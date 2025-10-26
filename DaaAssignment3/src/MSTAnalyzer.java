import javax.swing.*;
import java.io.*;
import java.util.*;

public class MSTAnalyzer {
    private PrimMST prim = new PrimMST();
    private KruskalMST kruskal = new KruskalMST();
    private List<Result> results = new ArrayList<>();
    private List<Graph> allGraphs = new ArrayList<>();

    public static void main(String[] args) {
        MSTAnalyzer analyzer = new MSTAnalyzer();

        GraphGenerator generator = new GraphGenerator();
        analyzer.allGraphs = generator.generateAllGraphs();
        analyzer.analyzeGraphs();
        analyzer.saveToCSV();
        analyzer.saveSummaryCSV();   // Summary statistics
        analyzer.saveToJSON();

        // first 5graphs in gui
        List<Graph> smallGraphs = analyzer.allGraphs.subList(0, Math.min(5, analyzer.allGraphs.size()));
        SwingUtilities.invokeLater(() -> {
            new MSTGUI(smallGraphs).setVisible(true);
        });

        System.out.println("Analysis complete! Generated " + analyzer.allGraphs.size() + " graphs.");
    }

    public void analyzeGraphs() {
        for (int i = 0; i < allGraphs.size(); i++) {
            Graph graph = allGraphs.get(i);
            String sizeCategory = getSizeCategory(i);
            String graphId = sizeCategory + "_" + (i + 1);

            System.out.println("Processing " + graphId + " - " +
                    graph.getVertices() + " vertices, " +
                    graph.getEdges().size() + " edges");


            MSTResult primResult = prim.findMST(graph);
            MSTResult kruskalResult = kruskal.findMST(graph);

            results.add(new Result(graphId, "Prim", graph.getVertices(),
                    graph.getEdges().size(), primResult.getTotalCost(),
                    primResult.getExecutionTime(), primResult.getOperations()));

            results.add(new Result(graphId, "Kruskal", graph.getVertices(),
                    graph.getEdges().size(), kruskalResult.getTotalCost(),
                    kruskalResult.getExecutionTime(), kruskalResult.getOperations()));
        }
    }

    private String getSizeCategory(int index) {
        if (index < 5) return "small";
        if (index < 15) return "medium";
        if (index < 25) return "large";
        return "xlarge";
    }


        public void saveToCSV() {
        try (PrintWriter writer = new PrintWriter("mst_results.csv")) {
            writer.println("id,algorithm,vertices,edges,total_cost,execution_time,operation_count");
            for (Result result : results) {
                writer.printf("%s,%s,%d,%d,%d,%.3f,%d\n",
                        result.id, result.algorithm, result.vertices, result.edges,
                        result.totalCost, result.executionTime, result.operationCount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class Result {
        String id, algorithm;
        int vertices, edges, totalCost, operationCount;
        double executionTime;

        Result(String id, String algorithm, int vertices, int edges,
               int totalCost, double executionTime, int operationCount) {
            this.id = id; this.algorithm = algorithm; this.vertices = vertices;
            this.edges = edges; this.totalCost = totalCost;
            this.executionTime = executionTime; this.operationCount = operationCount;
        }
    }
    public void saveSummaryCSV() {
        try (PrintWriter writer = new PrintWriter("performance_summary.csv")) {
            writer.println("size_category,algorithm,avg_vertices,avg_edges,avg_cost,avg_time_ms,avg_operations,min_time_ms,max_time_ms");

            Map<String, List<Result>> resultsByCategory = new HashMap<>();
            resultsByCategory.put("small", new ArrayList<>());
            resultsByCategory.put("medium", new ArrayList<>());
            resultsByCategory.put("large", new ArrayList<>());
            resultsByCategory.put("xlarge", new ArrayList<>());

            // Group results by category and algorithm
            for (Result result : results) {
                String category = result.id.split("_")[0];
                resultsByCategory.get(category).add(result);
            }

            // Calculate averages for each category and algorithm
            for (String category : resultsByCategory.keySet()) {
                List<Result> categoryResults = resultsByCategory.get(category);

                // Separate Prim and Kruskal results
                List<Result> primResults = new ArrayList<>();
                List<Result> kruskalResults = new ArrayList<>();

                for (Result result : categoryResults) {
                    if (result.algorithm.equals("Prim")) primResults.add(result);
                    else kruskalResults.add(result);
                }

                // Write Prim summary
                if (!primResults.isEmpty()) {
                    writer.printf("%s,Prim,%.1f,%.1f,%.1f,%.3f,%.1f,%.3f,%.3f\n",
                            category,
                            primResults.stream().mapToInt(r -> r.vertices).average().orElse(0),
                            primResults.stream().mapToInt(r -> r.edges).average().orElse(0),
                            primResults.stream().mapToInt(r -> r.totalCost).average().orElse(0),
                            primResults.stream().mapToDouble(r -> r.executionTime).average().orElse(0),
                            primResults.stream().mapToInt(r -> r.operationCount).average().orElse(0),
                            primResults.stream().mapToDouble(r -> r.executionTime).min().orElse(0),
                            primResults.stream().mapToDouble(r -> r.executionTime).max().orElse(0)
                    );
                }

                // Write Kruskal summary
                if (!kruskalResults.isEmpty()) {
                    writer.printf("%s,Kruskal,%.1f,%.1f,%.1f,%.3f,%.1f,%.3f,%.3f\n",
                            category,
                            kruskalResults.stream().mapToInt(r -> r.vertices).average().orElse(0),
                            kruskalResults.stream().mapToInt(r -> r.edges).average().orElse(0),
                            kruskalResults.stream().mapToInt(r -> r.totalCost).average().orElse(0),
                            kruskalResults.stream().mapToDouble(r -> r.executionTime).average().orElse(0),
                            kruskalResults.stream().mapToInt(r -> r.operationCount).average().orElse(0),
                            kruskalResults.stream().mapToDouble(r -> r.executionTime).min().orElse(0),
                            kruskalResults.stream().mapToDouble(r -> r.executionTime).max().orElse(0)
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveToJSON() {
        try (PrintWriter writer = new PrintWriter("assign_3_output.json")) {
            writer.println("{");
            writer.println("  \"results\": [");

            for (int i = 0; i < results.size(); i++) {
                Result result = results.get(i);
                writer.printf("    {\"id\": \"%s\", \"algorithm\": \"%s\", \"vertices\": %d, ",
                        result.id, result.algorithm, result.vertices);
                writer.printf("\"edges\": %d, \"total_cost\": %d, ",
                        result.edges, result.totalCost);
                writer.printf("\"execution_time\": %.3f, \"operation_count\": %d}",
                        result.executionTime, result.operationCount);
                writer.println(i < results.size()-1 ? "," : "");
            }

            writer.println("  ]");
            writer.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
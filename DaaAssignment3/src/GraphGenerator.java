import java.util.*;

public class GraphGenerator {
    private Random random = new Random();

    public List<Graph> generateAllGraphs() {
        List<Graph> graphs = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int vertices = random.nextInt(26) + 5; // 5-30
            graphs.add(generateGraph(vertices, 0.6));
        }

        for (int i = 0; i < 10; i++) {
            int vertices = random.nextInt(271) + 30; // 30-300
            graphs.add(generateGraph(vertices, 0.4));
        }

        for (int i = 0; i < 10; i++) {
            int vertices = random.nextInt(701) + 300; // 300-1000
            graphs.add(generateGraph(vertices, 0.2));
        }

        for (int i = 0; i < 3; i++) {
            int vertices = random.nextInt(2001) + 1000; // 1000-3000
            graphs.add(generateGraph(vertices, 0.1));
        }

        return graphs;
    }

    private Graph generateGraph(int vertices, double density) {
        Graph graph = new Graph(vertices);
        Set<String> existingEdges = new HashSet<>();

        for (int i = 1; i < vertices; i++) {
            int parent = random.nextInt(i);
            int weight = random.nextInt(100) + 1;
            graph.addEdge(i, parent, weight);
            existingEdges.add(i + "," + parent);
            existingEdges.add(parent + "," + i);
        }

        int maxPossibleEdges = vertices * (vertices - 1) / 2;
        int targetEdges = (int)((vertices - 1) + (maxPossibleEdges - (vertices - 1)) * density);

        while (graph.getEdges().size() < targetEdges) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);

            if (u != v && !existingEdges.contains(u + "," + v)) {
                int weight = random.nextInt(100) + 1;
                graph.addEdge(u, v, weight);
                existingEdges.add(u + "," + v);
                existingEdges.add(v + "," + u);
            }
        }

        return graph;
    }
}
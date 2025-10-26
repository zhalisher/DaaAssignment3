import java.util.*;

public class KruskalMST {
    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();

        int vertices = graph.getVertices();
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        Collections.sort(edges);
        int sortOperations = edges.size() * (int)(Math.log(edges.size()) / Math.log(2)); // Approximate sort operations

        UnionFind uf = new UnionFind(vertices);
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        for (Edge edge : edges) {
            if (mstEdges.size() == vertices - 1) break;

            if (uf.union(edge.getU(), edge.getV())) {
                mstEdges.add(edge);
                totalCost += edge.getWeight();
            }
        }

        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;
        int totalOperations = sortOperations + uf.getOperations();

        return new MSTResult(totalCost, mstEdges, executionTime, totalOperations);
    }
}
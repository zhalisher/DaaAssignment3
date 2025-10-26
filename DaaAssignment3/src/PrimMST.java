import java.util.*;

public class PrimMST {
    private int operations;

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        operations = 0;

        int vertices = graph.getVertices();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();

        visited[0] = true;
        operations++;

        for (Edge edge : graph.getAdjacencyList().get(0)) {
            minHeap.offer(edge);
            operations++;
        }

        while (!minHeap.isEmpty() && mstEdges.size() < vertices - 1) {
            Edge edge = minHeap.poll();
            operations++;

            int u = edge.getU();
            int v = edge.getV();
            int weight = edge.getWeight();

            int nextVertex = visited[u] && !visited[v] ? v :
                    (!visited[u] && visited[v] ? u : -1);

            operations += 2;
            if (nextVertex != -1) {
                visited[nextVertex] = true;
                mstEdges.add(edge);
                totalCost += weight;
                operations += 3;

                for (Edge nextEdge : graph.getAdjacencyList().get(nextVertex)) {
                    operations++;
                    if (!visited[nextEdge.getU()] || !visited[nextEdge.getV()]) {
                        minHeap.offer(nextEdge);
                        operations++;
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        return new MSTResult(totalCost, mstEdges, executionTime, operations);
    }
}
import java.util.*;

public class Graph {
    private int vertices;
    private List<Edge> edges;
    private List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.adjacencyList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        Edge edge = new Edge(u, v, weight);
        edges.add(edge);
        adjacencyList.get(u).add(edge);
        adjacencyList.get(v).add(edge);
    }

    public int getVertices() { return vertices; }
    public List<Edge> getEdges() { return edges; }
    public List<List<Edge>> getAdjacencyList() { return adjacencyList; }
}
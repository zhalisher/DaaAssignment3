public class Edge implements Comparable<Edge> {
    private int u;
    private int v;
    private int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int getU() { return u; }
    public int getV() { return v; }
    public int getWeight() { return weight; }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "[" + u + ", " + v + ", " + weight + "]";
    }
}
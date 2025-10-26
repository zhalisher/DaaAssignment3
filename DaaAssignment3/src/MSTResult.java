import java.util.List;

public class MSTResult {
    private int totalCost;
    private List<Edge> mstEdges;
    private double executionTime;
    private int operations;

    public MSTResult(int totalCost, List<Edge> mstEdges, double executionTime, int operations) {
        this.totalCost = totalCost;
        this.mstEdges = mstEdges;
        this.executionTime = executionTime;
        this.operations = operations;
    }

    // Getters
    public int getTotalCost() { return totalCost; }
    public List<Edge> getMstEdges() { return mstEdges; }
    public double getExecutionTime() { return executionTime; }
    public int getOperations() { return operations; }
}
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MSTGUI extends JFrame {
    private List<Graph> graphs;

    public MSTGUI(List<Graph> graphs) {
        this.graphs = graphs;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("MST - 5 Small Graphs Visualization");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 3, 10, 10));

        for (int i = 0; i < graphs.size(); i++) {
            add(new GraphPanel(graphs.get(i), "Graph " + (i + 1)));
        }

        pack();
        setSize(1200, 800);
        setLocationRelativeTo(null);
    }
}

class GraphPanel extends JPanel {
    private Graph graph;
    private String title;

    public GraphPanel(Graph graph, String title) {
        this.graph = graph;
        this.title = title;
        setPreferredSize(new Dimension(300, 300));
        setBorder(BorderFactory.createTitledBorder(title));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int vertices = graph.getVertices();
        Point[] positions = new Point[vertices];
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 40;

        for (int i = 0; i < vertices; i++) {
            double angle = 2 * Math.PI * i / vertices;
            int x = centerX + (int)(radius * Math.cos(angle));
            int y = centerY + (int)(radius * Math.sin(angle));
            positions[i] = new Point(x, y);
        }

        g.setColor(Color.LIGHT_GRAY);
        for (Edge edge : graph.getEdges()) {
            Point p1 = positions[edge.getU()];
            Point p2 = positions[edge.getV()];
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        g.setColor(Color.RED);
        for (int i = 0; i < vertices; i++) {
            Point p = positions[i];
            g.fillOval(p.x - 8, p.y - 8, 16, 16);
            g.setColor(Color.BLACK);
            g.drawString("" + i, p.x - 3, p.y + 3);
            g.setColor(Color.RED);
        }

        g.setColor(Color.BLACK);
        g.drawString("Vertices: " + vertices, 10, 20);
        g.drawString("Edges: " + graph.getEdges().size(), 10, 35);
    }
}
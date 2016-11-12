/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Alternative;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author mauricio
 */
public class GraphVisual extends JFrame {

    private Map<Alternative, List<Alternative>> graph;
    private DirectedGraph<String, String> g;

    public GraphVisual(Map<Alternative, List<Alternative>> graph) {
        super("Solución PROMETHEE 2");
        this.graph = graph;
        showGraph();
    }

    public void showGraph() {
        g = new DirectedSparseGraph<>();

        for (Alternative a : graph.keySet()) {
            g.addVertex(a.getName());
            for (Alternative e : graph.get(a)) {
                g.addEdge(a.getName() + e.getName(), a.getName(), e.getName());
            }
        }

        Layout<String, String> layout = new CircleLayout(g);
        layout.setSize(new Dimension(370, 300));

        Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
            public Paint transform(String i) {
                return Color.GREEN;
            }
        };

        BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.setPreferredSize(new Dimension(420, 350));

        this.setLocation(750, 0);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().add(vv);
    }
}

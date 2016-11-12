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
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author mauricio
 */
public class GraphVisual extends JFrame {

    private Map<Alternative, List<Alternative>> graph;
    private DirectedGraph<String, String> g;

    private Stack<Alternative> s = new Stack<>();
    private TreeSet<Alternative> visited = new TreeSet<>();

    private void dfs(Alternative node, boolean first) {
        visited.add(node);
        for (Alternative nei : graph.get(node)) {
            if (!visited.contains(nei)) {
                if (!first) {
                    System.out.println(node.getName() + " -> " + nei.getName());
                    g.addEdge(node.getName() + nei.getName(), node.getName(), nei.getName());
                }
                dfs(nei, first);
            }
        }
        if (first) {
            s.push(node);
        }
    }

    public GraphVisual(Map<Alternative, List<Alternative>> graph) {
        super();
        this.graph = graph;
        showGraph();
    }

    public void showGraph() {
        g = new DirectedSparseGraph<>();

        Alternative start = null;
        for (Alternative a : graph.keySet()) {
            g.addVertex(a.getName());
            for(Alternative e : graph.get(a)){
                g.addEdge(a.getName()+e.getName(), a.getName(),e.getName());
            }
            start = a;
        }
        
        
        
        /*dfs(start, true);
        visited.clear();

        while (!s.empty()) {
            if (!visited.contains(s.peek())) {
                dfs(s.peek(), false);
            }
            s.pop();
        }*/

        // The Layout<V, E> is parameterized by the vertex and edge types
        Layout<String, String> layout = new CircleLayout(g);
        layout.setSize(new Dimension(360, 300)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parameterized by the edge types

        Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
            public Paint transform(String i) {
                return Color.GREEN;
            }
        };

        BasicVisualizationServer<String, String> vv
                = new BasicVisualizationServer<>(layout);
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.setPreferredSize(new Dimension(410, 350)); //Sets the viewing area size

        this.setLocation(750, 0);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.getContentPane().add(vv);
    }

}

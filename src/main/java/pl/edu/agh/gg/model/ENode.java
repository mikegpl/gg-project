package pl.edu.agh.gg.model;

import org.graphstream.graph.implementations.AbstractGraph;

public class ENode extends GraphNode {
    public static final Character INIT_SYMBOL = 'E';

    public ENode(AbstractGraph graph, String id, Coordinates coordinates) {
        super(graph, id, INIT_SYMBOL, coordinates);
    }
}

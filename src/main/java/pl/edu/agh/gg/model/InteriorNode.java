package pl.edu.agh.gg.model;

import org.graphstream.graph.implementations.AbstractGraph;

public class InteriorNode extends GraphNode {
    private static final Character SYMBOL = 'I';

    public InteriorNode(AbstractGraph graph, String id, Coordinates coordinates) {
        super(graph, id, SYMBOL, coordinates);
    }

}

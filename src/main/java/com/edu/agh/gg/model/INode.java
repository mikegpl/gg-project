package com.edu.agh.gg.model;

import org.graphstream.graph.implementations.AbstractGraph;

public class INode extends GraphNode {
    private static final String SYMBOL = "I";

    public INode(AbstractGraph graph, String id, Coordinates coordinates) {
        super(graph, id, SYMBOL, coordinates);
    }
}

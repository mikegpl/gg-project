package com.edu.agh.gg.model;

import org.graphstream.graph.implementations.AbstractGraph;

public class ENode extends GraphNode {
    private static final String SYMBOL = "E";

    public ENode(AbstractGraph graph, String id, Coordinates coordinates) {
        super(graph, id, SYMBOL, coordinates);
    }
}

package com.edu.agh.gg.model;

import org.graphstream.graph.implementations.AbstractEdge;
import org.javatuples.Pair;

public class GraphEdge extends AbstractEdge {
    private final Pair<GraphNode, GraphNode> edgeNodes;
    private final double level;

    public GraphEdge(String id, Pair<GraphNode, GraphNode> edgeNodes, double level) {
        super(id, edgeNodes.getValue0(), edgeNodes.getValue1(), true);
        this.edgeNodes = edgeNodes;
        this.level = level;
    }

    public Pair<GraphNode, GraphNode> getEdgeNodes() {
        return edgeNodes;
    }

    public double getLevel() {
        return level;
    }

    public double getLength() {
        return Coordinates.distance(edgeNodes.getValue0().getCoordinates(), edgeNodes.getValue1().getCoordinates());
    }
}

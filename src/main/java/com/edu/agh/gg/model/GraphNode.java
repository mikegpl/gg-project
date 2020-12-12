package com.edu.agh.gg.model;

import com.edu.agh.gg.common.ElementAttributes;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.AbstractNode;
import org.graphstream.graph.implementations.SingleNode;

import java.util.Objects;

public abstract class GraphNode extends SingleNode {
    private final String symbol;
    private final Coordinates coordinates;

    protected GraphNode(AbstractGraph graph, String id, String symbol, double xCoordinate, double yCoordinate, double level) {
        this(graph, id, symbol, new Coordinates(xCoordinate, yCoordinate, level));
    }

    protected GraphNode(AbstractGraph graph, String id, String symbol, Coordinates coordinates) {
        super(graph, id);
        super.setAttribute(ElementAttributes.FROZEN_LAYOUT);
        this.symbol = symbol;
        this.coordinates = coordinates;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getXCoordinate() {
        return coordinates.getX();
    }

    public double getYCoordinate() {
        return coordinates.getY();
    }

    public double getLevel() {
        return coordinates.getLevel();
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    @Override
    public <T extends Edge> T getEdgeBetween(Node node) {
        for(AbstractNode e : this.neighborMap.keySet()){
            if(Objects.equals(e.getId(), node.getId())){
                return super.getEdgeBetween(e);
            }
        }
        return null;
    }
}

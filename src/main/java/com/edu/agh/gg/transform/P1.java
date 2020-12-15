package com.edu.agh.gg.transform;

import com.edu.agh.gg.model.Coordinates;
import com.edu.agh.gg.model.ENode;
import com.edu.agh.gg.model.GraphModel;
import com.edu.agh.gg.model.GraphNode;
import com.edu.agh.gg.model.INode;

import java.util.ArrayList;
import java.util.List;

public class P1 implements Transform {

    @Override
    public void transform(GraphModel graphModel, GraphNode graphNode) {
        int currentNodeLevel = (int) graphNode.getLevel();
        int nextLevel = currentNodeLevel + 1;

        double x = graphNode.getXCoordinate();
        double y = graphNode.getYCoordinate();

        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(graphModel.insertGraphNode(new INode(graphModel, getNodeName(graphNode, "i1"), new Coordinates(x - 0.5, -0.25, nextLevel))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, getNodeName(graphNode, "e1"), new Coordinates(x - 2.0, y - 2.0, nextLevel))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, getNodeName(graphNode, "e2"), new Coordinates(x - 2.0, y + 2.0, nextLevel))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, getNodeName(graphNode, "e3"), new Coordinates(x + 2.0, y + 2.0, nextLevel))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, getNodeName(graphNode, "e4"), new Coordinates(x + 2.0, y - 2.0, nextLevel))));

        graphModel.insertGraphEdge(getEdgeName(graphNode, nodes.get(0)), graphNode, nodes.get(0));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(1)), nodes.get(0), nodes.get(1));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(2)), nodes.get(0), nodes.get(2));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(3)), nodes.get(0), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(4)), nodes.get(0), nodes.get(4));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(1), nodes.get(2)), nodes.get(1), nodes.get(2));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(2), nodes.get(3)), nodes.get(2), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(3), nodes.get(4)), nodes.get(3), nodes.get(4));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(4), nodes.get(1)), nodes.get(4), nodes.get(1));
    }

    private String getNodeName(GraphNode graphNode, String nodeName) {
        return graphNode.getId() + nodeName;
    }

    private String getEdgeName(GraphNode first, GraphNode second) {
        return first.getId() + second.getId();

    }
}

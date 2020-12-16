package com.edu.agh.gg.transform;

import com.edu.agh.gg.model.Coordinates;
import com.edu.agh.gg.model.ENode;
import com.edu.agh.gg.model.GraphModel;
import com.edu.agh.gg.model.GraphNode;
import com.edu.agh.gg.model.InteriorNode;

import java.util.ArrayList;
import java.util.List;

public class P1 extends Transformation {

    @Override
    public boolean isApplicable(GraphModel graph, GraphNode interior) {
        return true;
    }

    @Override
    public void transform(GraphModel graphModel, GraphNode interiorNode) {
        int currentNodeLevel = (int) interiorNode.getLevel();
        int nextLevel = currentNodeLevel + 1;

        double x = interiorNode.getXCoordinate();
        double y = interiorNode.getYCoordinate();

        List<GraphNode> nodes = new ArrayList<>();

        GraphNode newInterior = new InteriorNode(graphModel, getNodeName(interiorNode, "i1"), new Coordinates(x - 0.5, -0.25, nextLevel));
        GraphNode e1 = new ENode(graphModel, getNodeName(interiorNode, "e1"), new Coordinates(x - 2.0, y - 2.0, nextLevel));
        GraphNode e2 = new ENode(graphModel, getNodeName(interiorNode, "e2"), new Coordinates(x - 2.0, y + 2.0, nextLevel));
        GraphNode e3 = new ENode(graphModel, getNodeName(interiorNode, "e3"), new Coordinates(x + 2.0, y + 2.0, nextLevel));
        GraphNode e4 = new ENode(graphModel, getNodeName(interiorNode, "e4"), new Coordinates(x + 2.0, y - 2.0, nextLevel));

        nodes.add(graphModel.insertGraphNode(newInterior));
        nodes.add(graphModel.insertGraphNode(e1));
        nodes.add(graphModel.insertGraphNode(e2));
        nodes.add(graphModel.insertGraphNode(e3));
        nodes.add(graphModel.insertGraphNode(e4));

        graphModel.insertGraphEdge(getEdgeName(interiorNode, nodes.get(0)), interiorNode, nodes.get(0));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(1)), nodes.get(0), nodes.get(1));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(2)), nodes.get(0), nodes.get(2));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(3)), nodes.get(0), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(4)), nodes.get(0), nodes.get(4));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(1), nodes.get(2)), nodes.get(1), nodes.get(2));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(2), nodes.get(3)), nodes.get(2), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(3), nodes.get(4)), nodes.get(3), nodes.get(4));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(4), nodes.get(1)), nodes.get(4), nodes.get(1));

        newInterior.addNeighbourENode(e1);
        newInterior.addNeighbourENode(e2);
        newInterior.addNeighbourENode(e3);
        newInterior.addNeighbourENode(e4);

        e1.addNeighbourENode(e2);
        e1.addNeighbourENode(e4);

        e2.addNeighbourENode(e3);
        e2.addNeighbourENode(e1);

        e3.addNeighbourENode(e4);
        e3.addNeighbourENode(e2);

        e4.addNeighbourENode(e1);
        e4.addNeighbourENode(e3);

    }

}

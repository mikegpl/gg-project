package com.edu.agh.gg.transform;

import com.edu.agh.gg.model.*;

import java.util.ArrayList;
import java.util.List;

public class P2 extends Transformation {


    @Override
    public boolean isApplicable(GraphModel graph, GraphNode interior) {

        GraphNode[] nodes = interior.getAdjacentENodes();

        return nodes.length >= 4 && graph.areNodesConnected(nodes[0], nodes[1]) &&
                graph.areNodesConnected(nodes[1], nodes[2]) &&
                graph.areNodesConnected(nodes[2], nodes[3]) &&
                graph.areNodesConnected(nodes[3], nodes[0]);
    }

    @Override
    public void transform(GraphModel graphModel, GraphNode interiorNode) {

        int currentNodeLevel = (int) interiorNode.getLevel();
        int nextLevel = currentNodeLevel + 1;

        double x = interiorNode.getXCoordinate();
        double y = interiorNode.getYCoordinate();

        List<GraphNode> nodes = new ArrayList<>();

        GraphNode newInterior1 = new InteriorNode(graphModel, getNodeName(interiorNode, "i1"), new Coordinates(x - 1.0, -0.25, nextLevel));
        GraphNode newInterior2 = new InteriorNode(graphModel, getNodeName(interiorNode, "i2"), new Coordinates(x + 1.0, -0.25, nextLevel));

        GraphNode n1 = new ENode(graphModel, getNodeName(interiorNode, "n1"), new Coordinates(x - 2.0, y + 2.0, nextLevel));
        GraphNode n2 = new ENode(graphModel, getNodeName(interiorNode, "n2"), new Coordinates(x + 0.0, y + 2.0, nextLevel));
        GraphNode n3 = new ENode(graphModel, getNodeName(interiorNode, "n3"), new Coordinates(x + 2.0, y + 2.0, nextLevel));
        GraphNode n4 = new ENode(graphModel, getNodeName(interiorNode, "n4"), new Coordinates(x + 2.0, y - 2.0, nextLevel));
        GraphNode n5 = new ENode(graphModel, getNodeName(interiorNode, "n5"), new Coordinates(x + 0.0, y - 2.0, nextLevel));
        GraphNode n6 = new ENode(graphModel, getNodeName(interiorNode, "n6"), new Coordinates(x - 2.0, y - 2.0, nextLevel));

        nodes.add(graphModel.insertGraphNode(newInterior1));
        nodes.add(graphModel.insertGraphNode(newInterior2));
        nodes.add(graphModel.insertGraphNode(n1));
        nodes.add(graphModel.insertGraphNode(n2));
        nodes.add(graphModel.insertGraphNode(n3));
        nodes.add(graphModel.insertGraphNode(n4));
        nodes.add(graphModel.insertGraphNode(n5));
        nodes.add(graphModel.insertGraphNode(n6));

        graphModel.insertGraphEdge(getEdgeName(interiorNode, nodes.get(0)), interiorNode, nodes.get(0));
        graphModel.insertGraphEdge(getEdgeName(interiorNode, nodes.get(1)), interiorNode, nodes.get(1));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(2)), nodes.get(0), nodes.get(2));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(3)), nodes.get(0), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(6)), nodes.get(0), nodes.get(6));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(0), nodes.get(7)), nodes.get(0), nodes.get(7));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(1), nodes.get(3)), nodes.get(1), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(1), nodes.get(4)), nodes.get(1), nodes.get(4));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(1), nodes.get(5)), nodes.get(1), nodes.get(5));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(1), nodes.get(6)), nodes.get(1), nodes.get(6));

        graphModel.insertGraphEdge(getEdgeName(nodes.get(2), nodes.get(3)), nodes.get(2), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(2), nodes.get(7)), nodes.get(2), nodes.get(7));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(6), nodes.get(7)), nodes.get(6), nodes.get(7));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(6), nodes.get(3)), nodes.get(6), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(6), nodes.get(5)), nodes.get(6), nodes.get(5));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(4), nodes.get(3)), nodes.get(4), nodes.get(3));
        graphModel.insertGraphEdge(getEdgeName(nodes.get(4), nodes.get(5)), nodes.get(4), nodes.get(5));


        newInterior1.addNeighbourENode(n1);
        newInterior1.addNeighbourENode(n2);
        newInterior1.addNeighbourENode(n5);
        newInterior1.addNeighbourENode(n6);

        newInterior2.addNeighbourENode(n2);
        newInterior2.addNeighbourENode(n3);
        newInterior2.addNeighbourENode(n4);
        newInterior2.addNeighbourENode(n5);

        n1.addNeighbourENode(n2);
        n1.addNeighbourENode(n6);

        n2.addNeighbourENode(n1);
        n2.addNeighbourENode(n3);
        n2.addNeighbourENode(n5);

        n3.addNeighbourENode(n4);
        n3.addNeighbourENode(n2);

        n4.addNeighbourENode(n5);
        n4.addNeighbourENode(n3);

        n5.addNeighbourENode(n6);
        n5.addNeighbourENode(n2);
        n5.addNeighbourENode(n4);

        n6.addNeighbourENode(n1);
        n6.addNeighbourENode(n5);

    }
}

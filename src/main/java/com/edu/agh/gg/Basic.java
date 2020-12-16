package com.edu.agh.gg;

import com.edu.agh.gg.model.Coordinates;
import com.edu.agh.gg.model.ENode;
import com.edu.agh.gg.model.GraphModel;
import com.edu.agh.gg.model.GraphNode;
import com.edu.agh.gg.model.InteriorNode;
import com.edu.agh.gg.visualization.Visualizer;

import java.util.ArrayList;
import java.util.List;

public class Basic {
    public static void main(String[] args) {
        GraphModel graphModel = generateGraphModel();
        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualizeUpTo(1);
    }

    private static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("Basic");

        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0.0, 0.0, 0))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(0.0, 2.0, 1))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(-2.0, 0.0, 1))));
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(2.0, 1.0, 1))));

        graphModel.insertGraphEdge("i1e1", nodes.get(0), nodes.get(1));
        graphModel.insertGraphEdge("i1e2", nodes.get(0), nodes.get(2));
        graphModel.insertGraphEdge("i1e3", nodes.get(0), nodes.get(3));
        graphModel.insertGraphEdge("e1e2", nodes.get(1), nodes.get(2));
        graphModel.insertGraphEdge("e2e3", nodes.get(2), nodes.get(3));
        graphModel.insertGraphEdge("e3e1", nodes.get(3), nodes.get(1));

        return graphModel;
    }
}

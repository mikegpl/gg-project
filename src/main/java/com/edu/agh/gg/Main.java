package com.edu.agh.gg;

import com.edu.agh.gg.model.*;
import com.edu.agh.gg.transform.*;
import com.edu.agh.gg.transform.Transformation;
import com.edu.agh.gg.visualization.Visualizer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GraphModel graphModel = generateGraphModel();

        Transformation p1 = new P1();
        Transformation p2 = new P2();

        if (p1.isApplicable(graphModel, graphModel.getGraphNode("e1").get())) {
            p1.transform(graphModel, graphModel.getGraphNode("e1").get());
        }

        if (p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1").get())) {
            p2.transform(graphModel, graphModel.getGraphNode("e1i1").get());
        }

        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualizeUpTo(3);

    }

    private static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("Main");

        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(0.0, 0.0, 1))));

        return graphModel;
    }
}

package com.edu.agh.gg;

import com.edu.agh.gg.model.Coordinates;
import com.edu.agh.gg.model.ENode;
import com.edu.agh.gg.model.GraphModel;
import com.edu.agh.gg.model.GraphNode;
import com.edu.agh.gg.transform.P1;
import com.edu.agh.gg.visualization.Visualizer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GraphModel graphModel = generateGraphModel();

        new P1().transform(graphModel, graphModel.getGraphNode("e1").get());

        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualizeUpTo(2);
    }

    private static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("Main");

        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(0.0, 0.0, 1))));

        return graphModel;
    }
}

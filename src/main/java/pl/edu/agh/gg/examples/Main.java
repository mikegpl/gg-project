package pl.edu.agh.gg.examples;

import pl.edu.agh.gg.transform.*;
import pl.edu.agh.gg.visualization.Visualizer;
import pl.edu.agh.gg.model.*;

public class Main {

    public static void main(String[] args) {
        GraphModel graphModel = generateGraphModel();

        Transformation p1 = new P1();
        Transformation p2 = new P2();

        if (p1.isApplicable(graphModel, graphModel.getGraphNode("e1").get(), false)) {
            p1.transform(graphModel, graphModel.getGraphNode("e1").get(), false);
        }

        if (p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1").get(), false)) {
            p2.transform(graphModel, graphModel.getGraphNode("e1i1").get(), false);
        }

        if (p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1i1").get(), false)) {
            p2.transform(graphModel, graphModel.getGraphNode("e1i1i1").get(), false);
        }

        if (p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1i2").get(), true)) {
            p2.transform(graphModel, graphModel.getGraphNode("e1i1i2").get(), true);
        }

        if (p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1i2i1").get(), true)) {
            p2.transform(graphModel, graphModel.getGraphNode("e1i1i2i1").get(), true);
        }

        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualize();

    }

    public static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("Main");
        graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(0.0, 0.0, 1)));
        return graphModel;
    }
}

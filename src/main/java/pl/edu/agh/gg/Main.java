package pl.edu.agh.gg;

import pl.edu.agh.gg.transform.P2;
import pl.edu.agh.gg.transform.Transformation;
import pl.edu.agh.gg.visualization.Visualizer;
import pl.edu.agh.gg.model.Coordinates;
import pl.edu.agh.gg.model.ENode;
import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;
import pl.edu.agh.gg.transform.P1;

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

    public static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("Main");

        List<GraphNode> nodes = new ArrayList<>();
        nodes.add(graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(0.0, 0.0, 1))));

        return graphModel;
    }
}

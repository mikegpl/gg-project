package pl.edu.agh.gg.examples;

import pl.edu.agh.gg.model.*;
import pl.edu.agh.gg.transform.P6;
import pl.edu.agh.gg.transform.Transformation;
import pl.edu.agh.gg.visualization.Visualizer;

public class MainP6 {
    public static void main(String[] args) {
        GraphModel graphModel = generateGraphModel();

        Transformation p6 = new P6();

        p6.transformIfApplicable(graphModel, graphModel.getGraphNode("i1").get(), false);

        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualize();
    }

    private static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("P6");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode e12 = graphModel.insertGraphNode(new ENode(graphModel, "e12", new Coordinates(0, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1e2", i1, e2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("e1e12", e1, e12);
        graphModel.insertGraphEdge("e12e2", e12, e2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);

        graphModel.insertGraphEdge("e2e4", e2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        e1.addNeighbourENode(e12);
        e1.addNeighbourENode(e13);

        e12.addNeighbourENode(e1);
        e12.addNeighbourENode(e2);

        e2.addNeighbourENode(e12);
        e2.addNeighbourENode(e4);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        e4.addNeighbourENode(e2);
        e4.addNeighbourENode(e3);

        return graphModel;
    }
}

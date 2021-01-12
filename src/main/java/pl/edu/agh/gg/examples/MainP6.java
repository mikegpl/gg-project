package pl.edu.agh.gg.examples;

import pl.edu.agh.gg.model.*;
import pl.edu.agh.gg.transform.P6;
import pl.edu.agh.gg.transform.Transformation;
import pl.edu.agh.gg.visualization.Visualizer;

public class MainP6 {
    public static void main(String[] args) {
        GraphModel graphModel = advancedModel();

        Transformation p6 = new P6();

        System.out.println(p6.isApplicable(graphModel, graphModel.getGraphNode("i1i4").get(), false));
        p6.transformIfApplicable(graphModel, graphModel.getGraphNode("i1i4").get(), false);

        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualize();
    }

    private static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("P6");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 1)));
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

    private static GraphModel firstLevel() {
        GraphModel graphModel = new GraphModel("P6-test");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode e2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1e2", i1, e2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("e1e2", e1, e2);
        graphModel.insertGraphEdge("e2e4", e2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);
        graphModel.insertGraphEdge("e3e1", e3, e1);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e2);
        i1.addNeighbourENode(e3);
        i1.addNeighbourENode(e4);

        e1.addNeighbourENode(e2);
        e1.addNeighbourENode(e3);

        e2.addNeighbourENode(e1);
        e2.addNeighbourENode(e4);

        e4.addNeighbourENode(e2);
        e4.addNeighbourENode(e3);

        e3.addNeighbourENode(e4);
        e3.addNeighbourENode(e1);

        return graphModel;
    }

    public static GraphModel advancedModel() {
        GraphModel graphModel = firstLevel();

        GraphNode I = graphModel.getGraphNode("i1").get();
        GraphNode i1, i2, i3, i4;
        GraphNode e1, e12, e2, e13, e14, e24, e3, e34, e4, e1424, e1434;

        i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1i1", new Coordinates(-0.5, 0.5, 1)));
        i2 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1i2", new Coordinates(0.5, 0.5, 1)));
        i3 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1i3", new Coordinates(-0.5, -0.5, 1)));
        i4 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1i4", new Coordinates(0.5, -0.5, 1)));

        e1 = graphModel.insertGraphNode(new ENode(graphModel, "i1e1", new Coordinates(-1, 1, 1)));
        e12 = graphModel.insertGraphNode(new ENode(graphModel, "i1e12", new Coordinates(0, 1, 1)));
        e2 = graphModel.insertGraphNode(new ENode(graphModel, "i1e2", new Coordinates(1, 1, 1)));
        e13 = graphModel.insertGraphNode(new ENode(graphModel, "i1e13", new Coordinates(-1, 0, 1)));
        e14 = graphModel.insertGraphNode(new ENode(graphModel, "i1e14", new Coordinates(0, 0, 1)));
        e24 = graphModel.insertGraphNode(new ENode(graphModel, "i1e24", new Coordinates(1, 0, 1)));
        e3 = graphModel.insertGraphNode(new ENode(graphModel, "i1e3", new Coordinates(-1, -1, 1)));
        e34 = graphModel.insertGraphNode(new ENode(graphModel, "i1e34", new Coordinates(0, -1, 1)));
        e4 = graphModel.insertGraphNode(new ENode(graphModel, "i1e4", new Coordinates(1, -1, 1)));
        e1424 = graphModel.insertGraphNode(new ENode(graphModel, "i1e1424", new Coordinates(0.5, 0, 1)));
        e1434 = graphModel.insertGraphNode(new ENode(graphModel, "i1e1434", new Coordinates(0, -0.5, 1)));

        graphModel.insertGraphEdge("i1i1i1", I, i1);
        graphModel.insertGraphEdge("i1i1i2", I, i2);
        graphModel.insertGraphEdge("i1i1i3", I, i3);
        graphModel.insertGraphEdge("i1i1i4", I, i4);

        graphModel.insertGraphEdge("i1e1i1e12", e1, e12);
        graphModel.insertGraphEdge("i1e12i1e2", e12, e2);
        graphModel.insertGraphEdge("i1e1i1e13", e1, e13);
        graphModel.insertGraphEdge("i1e12i1e14", e12, e14);
        graphModel.insertGraphEdge("i1e2i1e24", e2, e24);
        graphModel.insertGraphEdge("i1e13i1e14", e13, e14);
        graphModel.insertGraphEdge("i1e14i1e1424", e14, e1424);
        graphModel.insertGraphEdge("i1e1424i1e24", e1424, e24);
        graphModel.insertGraphEdge("i1e13i1e3", e13, e3);
//        graphModel.insertGraphEdge("i1e14i1e34",e14, e34);
        graphModel.insertGraphEdge("i1e14i1e1434", e14, e1434);
        graphModel.insertGraphEdge("i1e1434i1e34", e1434, e34);

        graphModel.insertGraphEdge("i1e24i1e4", e24, e4);
        graphModel.insertGraphEdge("i1e3i1e34", e3, e34);
        graphModel.insertGraphEdge("i1e34i1e4", e34, e4);

        graphModel.insertGraphEdge("i1e1i1", e1, i1);
        graphModel.insertGraphEdge("i1e12i1", e12, i1);
        graphModel.insertGraphEdge("i1e14i1", e14, i1);
        graphModel.insertGraphEdge("i1e13i1", e13, i1);

        graphModel.insertGraphEdge("i1e12i2", e12, i2);
        graphModel.insertGraphEdge("i1e2i2", e2, i2);
        graphModel.insertGraphEdge("i1e24i2", e24, i2);
        graphModel.insertGraphEdge("i1e14i2", e14, i2);

        graphModel.insertGraphEdge("i1e13i3", e13, i3);
        graphModel.insertGraphEdge("i1e14i3", e14, i3);
        graphModel.insertGraphEdge("i1e3i3", e3, i3);
        graphModel.insertGraphEdge("i1e34i3", e34, i3);

        graphModel.insertGraphEdge("i1e14i4", e14, i4);
        graphModel.insertGraphEdge("i1e24i4", e24, i4);
        graphModel.insertGraphEdge("i1e34i4", e34, i4);
        graphModel.insertGraphEdge("i1e4i4", e4, i4);

        e1.addNeighbourENode(e12);
        e1.addNeighbourENode(e13);

        e12.addNeighbourENode(e2);
        e12.addNeighbourENode(e1);
        e12.addNeighbourENode(e14);

        e2.addNeighbourENode(e12);
        e2.addNeighbourENode(e24);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e14);
        e13.addNeighbourENode(e3);

        e14.addNeighbourENode(e13);
        e14.addNeighbourENode(e12);
//        e14.addNeighbourENode(e34);
        e14.addNeighbourENode(e1434);
        e14.addNeighbourENode(e1424);

        e24.addNeighbourENode(e1424);
        e24.addNeighbourENode(e2);
        e24.addNeighbourENode(e4);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e34);

        e1434.addNeighbourENode(e34);
        e1434.addNeighbourENode(e14);

        e1424.addNeighbourENode(e24);
        e1424.addNeighbourENode(e14);

        e34.addNeighbourENode(e3);
//        e34.addNeighbourENode(e14);
        e34.addNeighbourENode(e1434);
        e34.addNeighbourENode(e4);

        e4.addNeighbourENode(e34);
        e4.addNeighbourENode(e24);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e12);
        i1.addNeighbourENode(e14);
        i1.addNeighbourENode(e13);

        i2.addNeighbourENode(e12);
        i2.addNeighbourENode(e2);
        i2.addNeighbourENode(e24);
        i2.addNeighbourENode(e14);

        i3.addNeighbourENode(e13);
        i3.addNeighbourENode(e3);
        i3.addNeighbourENode(e14);
        i3.addNeighbourENode(e34);

        i4.addNeighbourENode(e14);
        i4.addNeighbourENode(e24);
        i4.addNeighbourENode(e4);
        i4.addNeighbourENode(e34);

//        Visualizer visualizer = new Visualizer(graphModel);
//        visualizer.visualize(1);

        return graphModel;
    }
}

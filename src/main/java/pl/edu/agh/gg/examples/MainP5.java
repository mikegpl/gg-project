package pl.edu.agh.gg.examples;

import pl.edu.agh.gg.model.*;
import pl.edu.agh.gg.transform.P5;
import pl.edu.agh.gg.transform.Transformation;
import pl.edu.agh.gg.visualization.Visualizer;

public class MainP5 {
    public static void main(String[] args) {
        GraphModel graphModel = generateGraphModel3();

        Transformation p5 = new P5();

        if (p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false)) {
            p5.transform(graphModel, graphModel.getGraphNode("i1").get(), false);
        }
        //p5.transform(graphModel, graphModel.getGraphNode("i1").get(), false);


        Visualizer visualizer = new Visualizer(graphModel);
        visualizer.visualize();
    }

    public static GraphModel generateGraphModel() {
        GraphModel graphModel = new GraphModel("P5");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode e2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1e2", i1, e2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("e1e2", e1, e2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        graphModel.insertGraphEdge("e2e4", e2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        e1.addNeighbourENode(e2);
        e1.addNeighbourENode(e13);

        e2.addNeighbourENode(e1);
        e2.addNeighbourENode(e4);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        e4.addNeighbourENode(e2);
        e4.addNeighbourENode(e3);

        return graphModel;
    }

    public static GraphModel generateGraphModel2() {
        GraphModel graphModel = new GraphModel("P51");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode e2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        GraphNode i2 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i2", new Coordinates(2, 0, 0)));
        GraphNode e5 = graphModel.insertGraphNode(new ENode(graphModel, "e5", new Coordinates(3, 1, 0)));
        GraphNode e6 = graphModel.insertGraphNode(new ENode(graphModel, "e6", new Coordinates(3, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1e2", i1, e2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("i2e2", i2, e2);
        graphModel.insertGraphEdge("i2e5", i2, e5);
        graphModel.insertGraphEdge("i2e4", i2, e4);
        graphModel.insertGraphEdge("i2e6", i2, e6);

        graphModel.insertGraphEdge("e1e2", e1, e2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        graphModel.insertGraphEdge("e2e4", e2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        graphModel.insertGraphEdge("e2e5", e2, e5);
        graphModel.insertGraphEdge("e5e6", e5, e6);
        graphModel.insertGraphEdge("e6e4", e6, e4);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        i2.addNeighbourENode(e2);
        i2.addNeighbourENode(e5);
        i2.addNeighbourENode(e4);
        i2.addNeighbourENode(e6);

        e1.addNeighbourENode(e2);
        e1.addNeighbourENode(e13);

        e2.addNeighbourENode(e1);
        e2.addNeighbourENode(e4);
        e2.addNeighbourENode(e5);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        e4.addNeighbourENode(e2);
        e4.addNeighbourENode(e3);
        e4.addNeighbourENode(e6);

        e5.addNeighbourENode(e2);
        e5.addNeighbourENode(e6);

        e6.addNeighbourENode(e4);
        e6.addNeighbourENode(e5);

        return graphModel;
    }

    public static GraphModel generateGraphModel3() {
        GraphModel graphModel = new GraphModel("P53");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode e2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        GraphNode i2 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i2", new Coordinates(2, 0, 0)));
        GraphNode e5 = graphModel.insertGraphNode(new ENode(graphModel, "e5", new Coordinates(3, 1, 0)));
        GraphNode e6 = graphModel.insertGraphNode(new ENode(graphModel, "e6", new Coordinates(3, -1, 0)));

        GraphNode i3 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i3", new Coordinates(0, -2, 0)));
        GraphNode e7 = graphModel.insertGraphNode(new ENode(graphModel, "e7", new Coordinates(-1, -3, 0)));
        GraphNode e8 = graphModel.insertGraphNode(new ENode(graphModel, "e8", new Coordinates(1, -3, 0)));

        GraphNode i4 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i4", new Coordinates(-2, 0, 0)));
        GraphNode e9 = graphModel.insertGraphNode(new ENode(graphModel, "e9", new Coordinates(-3, -1, 0)));
        GraphNode e10 = graphModel.insertGraphNode(new ENode(graphModel, "e10", new Coordinates(-3, 1, 0)));

        GraphNode i5 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i5", new Coordinates(0, 2, 0)));
        GraphNode e11 = graphModel.insertGraphNode(new ENode(graphModel, "e11", new Coordinates(-1, 3, 0)));
        GraphNode e12 = graphModel.insertGraphNode(new ENode(graphModel, "e12", new Coordinates(1, 3, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1e2", i1, e2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("i2e2", i2, e2);
        graphModel.insertGraphEdge("i2e5", i2, e5);
        graphModel.insertGraphEdge("i2e4", i2, e4);
        graphModel.insertGraphEdge("i2e6", i2, e6);

        graphModel.insertGraphEdge("i3e3", i3, e3);
        graphModel.insertGraphEdge("i3e4", i3, e4);
        graphModel.insertGraphEdge("i3e7", i3, e7);
        graphModel.insertGraphEdge("i3e8", i3, e8);

        graphModel.insertGraphEdge("i4e1", i4, e1);
        graphModel.insertGraphEdge("i4e3", i4, e3);
        graphModel.insertGraphEdge("i4e9", i4, e9);
        graphModel.insertGraphEdge("i4e10", i4, e10);

        graphModel.insertGraphEdge("i5e1", i5, e1);
        graphModel.insertGraphEdge("i5e2", i5, e2);
        graphModel.insertGraphEdge("i5e11", i5, e11);
        graphModel.insertGraphEdge("i5e12", i5, e12);

        graphModel.insertGraphEdge("e1e2", e1, e2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        graphModel.insertGraphEdge("e2e4", e2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        graphModel.insertGraphEdge("e2e5", e2, e5);
        graphModel.insertGraphEdge("e5e6", e5, e6);
        graphModel.insertGraphEdge("e6e4", e6, e4);

        graphModel.insertGraphEdge("e4e8", e4, e8);
        graphModel.insertGraphEdge("e8e7", e8, e7);
        graphModel.insertGraphEdge("e7e3", e7, e3);

        graphModel.insertGraphEdge("e3e9", e3, e9);
        graphModel.insertGraphEdge("e10e9", e10, e9);
        graphModel.insertGraphEdge("e10e1", e10, e1);

        graphModel.insertGraphEdge("e1e11", e1, e11);
        graphModel.insertGraphEdge("e11e12", e11, e12);
        graphModel.insertGraphEdge("e2e12", e2, e12);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        i2.addNeighbourENode(e2);
        i2.addNeighbourENode(e5);
        i2.addNeighbourENode(e4);
        i2.addNeighbourENode(e6);

        i3.addNeighbourENode(e3);
        i3.addNeighbourENode(e4);
        i3.addNeighbourENode(e7);
        i3.addNeighbourENode(e8);

        i4.addNeighbourENode(e1);
        i4.addNeighbourENode(e3);
        i4.addNeighbourENode(e9);
        i4.addNeighbourENode(e10);

        i5.addNeighbourENode(e1);
        i5.addNeighbourENode(e2);
        i5.addNeighbourENode(e11);
        i5.addNeighbourENode(e12);

        e1.addNeighbourENode(e2);
        e1.addNeighbourENode(e13);
        e1.addNeighbourENode(e10);
        e1.addNeighbourENode(e11);

        e2.addNeighbourENode(e1);
        e2.addNeighbourENode(e4);
        e2.addNeighbourENode(e5);
        e2.addNeighbourENode(e12);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);
        e3.addNeighbourENode(e7);
        e3.addNeighbourENode(e9);

        e4.addNeighbourENode(e2);
        e4.addNeighbourENode(e3);
        e4.addNeighbourENode(e6);
        e4.addNeighbourENode(e8);

        e5.addNeighbourENode(e2);
        e5.addNeighbourENode(e6);

        e6.addNeighbourENode(e4);
        e6.addNeighbourENode(e5);

        e7.addNeighbourENode(e3);
        e7.addNeighbourENode(e8);

        e8.addNeighbourENode(e4);
        e8.addNeighbourENode(e7);

        e9.addNeighbourENode(e3);
        e9.addNeighbourENode(e10);

        e10.addNeighbourENode(e1);
        e10.addNeighbourENode(e9);

        e11.addNeighbourENode(e1);
        e11.addNeighbourENode(e12);

        e12.addNeighbourENode(e2);
        e12.addNeighbourENode(e11);

        return graphModel;
    }

    public static GraphModel generateGraphModel4() {
        GraphModel graphModel = new GraphModel("P5");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(0, 0, 0)));
        GraphNode e2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1e2", i1, e2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("e1e2", e1, e2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        graphModel.insertGraphEdge("e2e4", e2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(e2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        e1.addNeighbourENode(e2);
        e1.addNeighbourENode(e13);

        e2.addNeighbourENode(e1);
        e2.addNeighbourENode(e4);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        e4.addNeighbourENode(e2);
        e4.addNeighbourENode(e3);

        return graphModel;
    }

    public static GraphModel generateGraphModel5() {
        GraphModel graphModel = new GraphModel("P5");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode i2 = graphModel.insertGraphNode(new ENode(graphModel, "i2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1i2", i1, i2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("e1i2", e1, i2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        graphModel.insertGraphEdge("i2e4", i2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(i2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        e1.addNeighbourENode(i2);
        e1.addNeighbourENode(e13);

        i2.addNeighbourENode(e1);
        i2.addNeighbourENode(e4);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        e4.addNeighbourENode(i2);
        e4.addNeighbourENode(e3);

        return graphModel;
    }

    public static GraphModel generateGraphModel6() {
        GraphModel graphModel = new GraphModel("P5");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        GraphNode i2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        graphModel.insertGraphEdge("i1i2", i1, i2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        //graphModel.insertGraphEdge("i1e4", i1, e4);

        graphModel.insertGraphEdge("e1i2", e1, i2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        graphModel.insertGraphEdge("i2e4", i2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        i1.addNeighbourENode(e1);
        i1.addNeighbourENode(i2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        e1.addNeighbourENode(i2);
        e1.addNeighbourENode(e13);

        i2.addNeighbourENode(e1);
        i2.addNeighbourENode(e4);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        e4.addNeighbourENode(i2);
        e4.addNeighbourENode(e3);

        return graphModel;
    }

    public static GraphModel generateGraphModel7() {
        GraphModel graphModel = new GraphModel("P5");

        GraphNode i1 = graphModel.insertGraphNode(new InteriorNode(graphModel, "i1", new Coordinates(0, 0, 0)));
        GraphNode e1 = graphModel.insertGraphNode(new ENode(graphModel, "e1", new Coordinates(-1, 1, 0)));
        //GraphNode i2 = graphModel.insertGraphNode(new ENode(graphModel, "e2", new Coordinates(1, 1, 0)));
        GraphNode e13 = graphModel.insertGraphNode(new ENode(graphModel, "e13", new Coordinates(-1, 0, 0)));
        GraphNode e3 = graphModel.insertGraphNode(new ENode(graphModel, "e3", new Coordinates(-1, -1, 0)));
        GraphNode e4 = graphModel.insertGraphNode(new ENode(graphModel, "e4", new Coordinates(1, -1, 0)));

        graphModel.insertGraphEdge("i1e1", i1, e1);
        //graphModel.insertGraphEdge("i1i2", i1, i2);
        graphModel.insertGraphEdge("i1e3", i1, e3);
        graphModel.insertGraphEdge("i1e4", i1, e4);

        //graphModel.insertGraphEdge("e1i2", e1, i2);
        graphModel.insertGraphEdge("e1e13", e1, e13);
        graphModel.insertGraphEdge("e13e3", e13, e3);
        //graphModel.insertGraphEdge("i2e4", i2, e4);
        graphModel.insertGraphEdge("e3e4", e3, e4);

        i1.addNeighbourENode(e1);
        //i1.addNeighbourENode(i2);
        i1.addNeighbourENode(e4);
        i1.addNeighbourENode(e3);

        //e1.addNeighbourENode(i2);
        e1.addNeighbourENode(e13);

        //i2.addNeighbourENode(e1);
        //i2.addNeighbourENode(e4);

        e13.addNeighbourENode(e1);
        e13.addNeighbourENode(e3);

        e3.addNeighbourENode(e13);
        e3.addNeighbourENode(e4);

        //e4.addNeighbourENode(i2);
        e4.addNeighbourENode(e3);

        return graphModel;
    }
}
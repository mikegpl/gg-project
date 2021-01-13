package pl.edu.agh.gg.transform;

import static org.junit.Assert.*;
import static pl.edu.agh.gg.examples.MainP5.generateGraphModel;
import static pl.edu.agh.gg.examples.MainP5.generateGraphModel3;
import static pl.edu.agh.gg.examples.MainP5.generateGraphModel4;
import static pl.edu.agh.gg.examples.MainP5.generateGraphModel5;

import org.javatuples.Pair;
import org.junit.Test;

import pl.edu.agh.gg.model.Coordinates;
import pl.edu.agh.gg.model.GraphEdge;
import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;
import pl.edu.agh.gg.transform.utils.MockGraphs;

import java.util.List;

public class P5TestSuite {

    @Test
    public void isApplicableP5TransformationTest1() {

        GraphModel graphModel = generateGraphModel3();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertTrue(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest2() {

        GraphModel graphModel = generateGraphModel3();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i2").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest3() { //TODO - label

        GraphModel graphModel = generateGraphModel5();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest4() {

        GraphModel graphModel = generateGraphModel4();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void transformP5TransformationTest() {

        GraphModel graphModel = generateGraphModel();
        Transformation p5 = new P5();

        if (p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false)){
            p5.transform(graphModel, graphModel.getGraphNode("i1").get(), false);
        }


        List<Pair<Character, Coordinates>> mockGraphP5 = MockGraphs.generateMockP5();

        for (Pair<Character, Coordinates> pair : mockGraphP5) {

            long count = graphModel.getGraphNodes().stream()
                    //.filter(n -> n.getCoordinates().equals(pair.getValue1())).count();
                    .filter(n -> n.getSymbol() == pair.getValue0() && n.getCoordinates().equals(pair.getValue1())).count();

            assertEquals(pair.toString(), 1L, count);
        }

    }

    @Test
    public void transformP5TransformationTest2() {

        GraphModel graphModel = generateGraphModel();
        Transformation p5 = new P5();

        if (p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false)){
            p5.transform(graphModel, graphModel.getGraphNode("i1").get(), false);
        }


        //List<Pair<Character, Coordinates>> mockGraphP5 = MockGraphs.generateMockP5();
        long count = 0;
        for (GraphEdge edge: graphModel.getEdges()){
            //System.out.println(edge.getId());
            count = count + 1;
        }
        //System.out.println(count);

        assertEquals(39, 1L, count);


    }


}
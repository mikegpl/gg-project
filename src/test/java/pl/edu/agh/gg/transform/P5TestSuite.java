package pl.edu.agh.gg.transform;

import org.javatuples.Pair;
import org.junit.Test;
import pl.edu.agh.gg.model.Coordinates;
import pl.edu.agh.gg.model.GraphEdge;
import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;
import pl.edu.agh.gg.transform.utils.MockGraphs;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static pl.edu.agh.gg.examples.MainP5.*;

public class P5TestSuite {

    @Test
    public void isApplicableP5TransformationTest1() { // poprawna lewa strona

        GraphModel graphModel = generateGraphModel3();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertTrue(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest2() { // niepoprawna lewa strona

        GraphModel graphModel = generateGraphModel3();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i2").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest3() { // błędna etykieta

        GraphModel graphModel = generateGraphModel5();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest4() { // błędne współrzędne

        GraphModel graphModel = generateGraphModel4();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest5() { // bez krawędzi

        GraphModel graphModel = generateGraphModel6();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void isApplicableP5TransformationTest6() { // bez wierzchołka

        GraphModel graphModel = generateGraphModel7();
        Transformation p5 = new P5();

        //p5.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false));
    }

    @Test
    public void transformP5TransformationTest() { // etykiety, wierzchołki, współrzędne

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
    public void transformP5TransformationTest2() { // krawędzie

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

    @Test
    public void transformP5TransformationTest3() { // poziomy

        GraphModel graphModel = generateGraphModel3();
        Transformation p5 = new P5();

        if (p5.isApplicable(graphModel, graphModel.getGraphNode("i1").get(), false)){
            p5.transform(graphModel, graphModel.getGraphNode("i1").get(), false);
        }

        List<Pair<Character, Coordinates>> mc = new ArrayList<>();
        for (GraphNode node: graphModel.getGraphNodes()){
            mc.add(new Pair<>(node.getSymbol(), node.getCoordinates()));
        }


        List<Pair<Character, Coordinates>> mockGraphP55 = MockGraphs.generateMockP55();
        int i = 0;
        for (Pair<Character, Coordinates> pair : mockGraphP55) {
            assertEquals(mc.get(i).getValue1().getLevel(), pair.getValue1().getLevel(), 0.001);
            i = i + 1;
        }

    }

}
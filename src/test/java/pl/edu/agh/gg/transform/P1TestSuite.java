package pl.edu.agh.gg.transform;

import static org.junit.Assert.*;
import static pl.edu.agh.gg.examples.Main.generateGraphModel;

import org.junit.Test;
import pl.edu.agh.gg.model.GraphModel;

public class P1TestSuite {

    @Test
    public void isApplicableP1TransformationTest() {

        GraphModel graphModel = generateGraphModel();
        Transformation p1 = new P1();

        assertTrue(p1.isApplicable(graphModel, graphModel.getGraphNode("e1").get(), false));
    }

    @Test
    public void transformP1TransformationTest() {

        GraphModel graphModel = generateGraphModel();
        Transformation p1 = new P1();
        Transformation p2 = new P2();

        p1.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertTrue(p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1").get(), false));
    }

    @Test
    public void nodeNameNotPresentTest() {

        GraphModel graphModel = generateGraphModel();

        assertFalse(graphModel.getGraphNode("e2").isPresent());
    }

    @Test
    public void initialLowerCaseCharTest() {

        GraphModel graphModel = generateGraphModel();
        Transformation p1 = new P1();
        p1.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertFalse(p1.isApplicable(graphModel, graphModel.getGraphNode("e1").get(), false));
    }
}

package pl.edu.agh.gg.transform;

import static org.junit.Assert.*;
import static pl.edu.agh.gg.Main.generateGraphModel;

import org.junit.Test;
import pl.edu.agh.gg.model.GraphModel;

public class P2TestSuite {

    @Test
    public void canApplyP2Transformation() {

        GraphModel graphModel = generateGraphModel();
        Transformation p1 = new P1();
        Transformation p2 = new P2();

        p1.transform(graphModel, graphModel.getGraphNode("e1").get(), false);

        assertTrue(p2.isApplicable(graphModel, graphModel.getGraphNode("e1i1").get(), false));
    }

}

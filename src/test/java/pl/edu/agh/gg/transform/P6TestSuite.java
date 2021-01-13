package pl.edu.agh.gg.transform;

import org.javatuples.Pair;
import org.junit.Test;
import pl.edu.agh.gg.common.ElementAttributes;
import pl.edu.agh.gg.model.*;
import pl.edu.agh.gg.transform.utils.MockGraphs;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static pl.edu.agh.gg.examples.MainP6.advancedModel;

public class P6TestSuite {

    static int VALID_GRAPH_MODEL_START_LEVEL = 2;

    static GraphModel firstLevel() {
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

    static GraphModel valid() {
        return advancedModel();
    }

    static GraphModel invalidMissingVertex() {
        GraphModel graphModel = valid();
        GraphNode my = graphModel.getGraphNode("i1e24").get();
        graphModel.removeGraphNode(my.getId());
        graphModel.removeGraphNode("i1e24");
        graphModel.getGraphNode("i1e1424").get().deleteNeighbourENode(my);
        graphModel.getGraphNode("i1e4").get().deleteNeighbourENode(my);
        graphModel.getGraphNode("i1i4").get().deleteNeighbourENode(my);
        return graphModel;
    }

    static GraphModel invalidMissingEdge() {
        GraphModel graphModel = valid();
        graphModel.deleteGraphEdge("i1e24i1e4");
        return graphModel;
    }

    static GraphModel invalidWrongLabel() {
        GraphModel graphModel = valid();
        graphModel.getGraphNode("i1e34").get().setAttribute(ElementAttributes.LABEL, "niepoprawna etykieta");
        return graphModel;
    }

    static GraphModel invalidWrongCoords() {
        GraphModel graphModel = valid();
        GraphNode node = graphModel.getGraphNode("i1e14").get();
        try {
            Field coords = node.getClass().getSuperclass().getDeclaredField("coordinates");
            coords.setAccessible(true);
            coords.set(node, new Coordinates(50, 50, 1));
        } catch (Exception e) {
            System.err.println(e);
        }
        return graphModel;
    }

    static Transformation p6 = new P6();

    @Test
    public void isApplicableAcceptsValidModelWithValidStartTest() {
        GraphModel validModel = valid();
        GraphNode validStart = validModel.getGraphNode("i1i4").get();
        GraphNode invalidStart = validModel.getGraphNode("i1i2").get();

        assertTrue(p6.isApplicable(validModel, validStart, false));
        assertFalse(p6.isApplicable(validModel, invalidStart, false));
    }

    @Test
    public void isApplicableRejectsModelWithMissingVertexTest() {
        GraphModel invalidMissingVertexModel = invalidMissingVertex();
        GraphNode validStart = invalidMissingVertexModel.getGraphNode("i1i4").get();
        assertFalse(p6.isApplicable(invalidMissingVertexModel, validStart, false));
    }

    @Test
    public void isApplicableAcceptsModelWithWrongLabels() {
        GraphModel invalidWrongLabelModel = invalidWrongLabel();
        GraphNode validStart = invalidWrongLabelModel.getGraphNode("i1i4").get();
        assertTrue(p6.isApplicable(invalidWrongLabelModel, validStart, false));
    }

    @Test
    public void isApplicableRejectsModelWithMissingEdge() {
        GraphModel invalidMissingEdgeModel = invalidMissingEdge();
        GraphNode validStart = invalidMissingEdgeModel.getGraphNode("i1i4").get();
        assertFalse(p6.isApplicable(invalidMissingEdgeModel, validStart, false));
    }

    @Test
    public void isApplicableRejectsModelWithWrongCoords() {
        GraphModel invalidWrongCoordsModel = invalidWrongCoords();
        GraphNode invalidCoordsStart = invalidWrongCoordsModel.getGraphNode("i1i4").get();
        assertFalse(p6.isApplicable(invalidWrongCoordsModel, invalidCoordsStart, false));
    }


    @Test
    public void transformChangesRightLevelTest() {
        GraphModel model = valid();
        GraphNode startNode = model.getGraphNode("i1i4").get();
        p6.transformIfApplicable(model, startNode, false);

        long beforeLevel1Nodes = valid().getGraphNodes().stream().filter(node -> node.getLevel() == 1).count();
        long afterLevel1Nodes = model.getGraphNodes().stream().filter(node -> node.getLevel() == 1).count();

        long afterLevel2Nodes = model.getGraphNodes().stream().filter(node -> node.getLevel() == 2).count();

        assertEquals(beforeLevel1Nodes, afterLevel1Nodes);
        assertEquals(13, afterLevel2Nodes);
    }

    @Test
    public void transformIntroducesVerticesWithRightCoordsTest() {
        GraphModel model = valid();
        GraphNode startNode = model.getGraphNode("i1i4").get();
        p6.transformIfApplicable(model, startNode, false);

        for (Pair<Character, Coordinates> pair : MockGraphs.generateMockP6()) {
            long count = model.getGraphNodes().stream()
                    .filter(n -> n.getSymbol() == pair.getValue0() && n.getCoordinates().equals(pair.getValue1())).count();

            assertEquals(pair.toString(), 1L, count);
        }
    }

}

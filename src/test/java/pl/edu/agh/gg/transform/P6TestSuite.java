package pl.edu.agh.gg.transform;

import org.javatuples.Pair;
import org.junit.Test;
import pl.edu.agh.gg.model.Coordinates;
import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;
import pl.edu.agh.gg.transform.utils.MockGraphs;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static pl.edu.agh.gg.examples.MainP6.advancedModel;
import static pl.edu.agh.gg.examples.MainP6.advancedSkewedModel;

public class P6TestSuite {

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

    static GraphModel invalidMissingEdge1() {
        GraphModel graphModel = valid();
        graphModel.deleteGraphEdge("i1e24i1e4");
        return graphModel;
    }

    static GraphModel invalidMissingEdge2() {
        GraphModel graphModel = valid();
        graphModel.deleteGraphEdge("i1e14i1e1424");
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
    public void isApplicableRejectsModelWithMissingEdge() {
        GraphModel invalidMissingEdgeModel1 = invalidMissingEdge1();
        GraphNode validStart1 = invalidMissingEdgeModel1.getGraphNode("i1i4").get();

        GraphModel invalidMissingEdgeModel2 = invalidMissingEdge2();
        GraphNode validStart2 = invalidMissingEdgeModel2.getGraphNode("i1i4").get();

        assertFalse(p6.isApplicable(invalidMissingEdgeModel1, validStart1, false));
        assertFalse(p6.isApplicable(invalidMissingEdgeModel2, validStart2, false));
    }

    @Test
    public void isApplicableRejectsModelWithWrongCoords() {
        GraphModel invalidWrongCoordsModel = invalidWrongCoords();
        GraphNode invalidCoordsStart = invalidWrongCoordsModel.getGraphNode("i1i4").get();
        assertFalse(p6.isApplicable(invalidWrongCoordsModel, invalidCoordsStart, false));
    }

    @Test
    public void isApplicableAcceptsModelWhenInodeNeighboursAreAddedInWeirdOrder() {
        GraphModel weirdModel = advancedSkewedModel();
        GraphNode validStart = weirdModel.getGraphNode("i1i4").get();

        assertTrue(p6.isApplicable(weirdModel, validStart, false));
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

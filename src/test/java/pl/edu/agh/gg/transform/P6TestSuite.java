package pl.edu.agh.gg.transform;

import org.junit.Test;
import pl.edu.agh.gg.common.ElementAttributes;
import pl.edu.agh.gg.model.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        graphModel.getGraphNode("i1e14").get().setAttribute(ElementAttributes.XY, 50, 40);
        return graphModel;
    }

    static Transformation p6 = new P6();

    @Test
    public void isApplicableP6TransformationTest() {
        GraphModel validModel = valid();
        GraphModel invalidMissingVertexModel = invalidMissingVertex();
        GraphModel invalidMissingEdgeModel = invalidMissingEdge();
        GraphModel invalidWrongLabelModel = invalidWrongLabel();
        GraphModel invalidWrongCoordsModel = invalidWrongCoords();

        GraphNode validValidStart = validModel.getGraphNode("i1i4").get();
        GraphNode validInvalidStart = validModel.getGraphNode("i1i2").get();
        GraphNode invalidVertexStart = invalidMissingVertexModel.getGraphNode("i1i4").get();
        GraphNode invalidEdgeStart = invalidMissingEdgeModel.getGraphNode("i1i4").get();
        GraphNode invalidLabelStart = invalidWrongLabelModel.getGraphNode("i1i4").get();
        GraphNode invalidCoordsStart = invalidWrongCoordsModel.getGraphNode("e1").get();

        assertFalse(p6.isApplicable(validModel, validInvalidStart, false));
        assertFalse(p6.isApplicable(invalidMissingVertexModel, invalidVertexStart, false));
        assertFalse(p6.isApplicable(invalidMissingEdgeModel, invalidEdgeStart, false));
        assertFalse(p6.isApplicable(invalidWrongCoordsModel, invalidCoordsStart, false));

        assertTrue(p6.isApplicable(invalidWrongLabelModel, invalidLabelStart, false));
        assertTrue(p6.isApplicable(validModel, validValidStart, false));
    }

    @Test
    public void transformP6TransformationTest() {
        GraphModel model = valid();
        GraphNode startNode = model.getGraphNode("e1").get();
        p6.transformIfApplicable(model, startNode, false);

        assertTrue(changesIntroducedOnRightLevel(model, VALID_GRAPH_MODEL_START_LEVEL + 1));
        assertTrue(graphModelNotBroken(model)); // model nie uszkadza większego grafu
        assertTrue(rightElementsIntroduced(model)); // czy ma wszystkie wierzchołki, krawędzie i etykiety
        assertTrue(coordinatesValid(model)); // czy współrzędne są poprawne
    }

    private boolean changesIntroducedOnRightLevel(GraphModel model, int level) {
        return false;
    }

    private boolean graphModelNotBroken(GraphModel graphModel) {
        return false;
    }

    private boolean rightElementsIntroduced(GraphModel model) {
        return false;
    }

    private boolean coordinatesValid(GraphModel model) {
        return false;
    }
}

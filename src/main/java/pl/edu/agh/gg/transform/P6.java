package pl.edu.agh.gg.transform;

import pl.edu.agh.gg.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P6 extends Transformation {

    private static final class LeftSide {
        InteriorNode i;
        ENode x1;
        ENode x12;
        ENode x2;
        ENode x13;
        ENode x3;
        ENode x4;
    }

    private static final class RightSide {
        InteriorNode i1;
        InteriorNode i2;
        InteriorNode i3;
        InteriorNode i4;
        ENode x1;
        ENode x12;
        ENode x2;
        ENode x13;
        ENode x14;
        ENode x24;
        ENode x3;
        ENode x34;
        ENode x4;
    }

    protected LeftSide leftSide;
    private GraphModel graphModel;
    private InteriorNode interiorNode;

    @Override
    public boolean isApplicable(GraphModel graphModel, GraphNode interiorNode, boolean horizontal) {
        if (!(interiorNode instanceof InteriorNode) || !interiorNode.isSymbolUpperCase()) {
            return false;
        }
        this.graphModel = graphModel;
        this.interiorNode = (InteriorNode) interiorNode;
        Optional<LeftSide> leftSideCandidate = orientate();
        leftSideCandidate.ifPresent(side -> this.leftSide = side);
        return leftSideCandidate.isPresent();
    }

    @Override
    public void transform(GraphModel graphModel, GraphNode interiorNode, boolean horizontal) {

        LeftSide l = leftSide;

        // Change symbol to lowercase
        l.i.symbolToLowerCase();

        RightSide r = new RightSide();

        // Push down nodes from the previous level
        r.x1 = pushDown(l.x1, "e1");
        r.x12 = pushDown(l.x12, "e12");
        r.x2 = pushDown(l.x2, "e2");
        r.x13 = pushDown(l.x13, "e13");
        r.x3 = pushDown(l.x3, "e3");
        r.x4 = pushDown(l.x4, "e4");

        // Create new edge nodes
        r.x14 = pushDown(l.i, "e14");
        r.x24 = new ENode(graphModel, getNodeName(l.i, "e24"), mid(r.x2, r.x4));
        r.x34 = new ENode(graphModel, getNodeName(l.i, "e34"), mid(r.x3, r.x4));

        // Create new interior nodes
        r.i1 = new InteriorNode(graphModel, getNodeName(l.i, "i1"), mid(r.x1, r.x14));
        r.i2 = new InteriorNode(graphModel, getNodeName(l.i, "i2"), mid(r.x2, r.x14));
        r.i3 = new InteriorNode(graphModel, getNodeName(l.i, "i3"), mid(r.x3, r.x14));
        r.i4 = new InteriorNode(graphModel, getNodeName(l.i, "i4"), mid(r.x4, r.x14));

        // Insert nodes into graph
        Stream.of(
                r.x1, r.x12, r.x2, r.x13, r.x14, r.x24, r.x3, r.x34, r.x4,
                r.i1, r.i2, r.i3, r.i4
        ).forEach(graphModel::insertGraphNode);

        // Add interior-interior edges
        addEdge(l.i, r.i1);
        addEdge(l.i, r.i2);
        addEdge(l.i, r.i3);
        addEdge(l.i, r.i4);

        // Add interior-edge edges (clockwise)
        addEdge(r.i1, r.x1);
        addEdge(r.i1, r.x12);
        addEdge(r.i1, r.x14);
        addEdge(r.i1, r.x13);

        addEdge(r.i2, r.x12);
        addEdge(r.i2, r.x2);
        addEdge(r.i2, r.x24);
        addEdge(r.i2, r.x14);

        addEdge(r.i3, r.x13);
        addEdge(r.i3, r.x14);
        addEdge(r.i3, r.x34);
        addEdge(r.i3, r.x3);

        addEdge(r.i4, r.x14);
        addEdge(r.i4, r.x24);
        addEdge(r.i4, r.x4);
        addEdge(r.i4, r.x34);

        // Add edge-edge edges (clockwise starting from noon <=> by rows right to left)
        addEdge(r.x2, r.x24);
        addEdge(r.x12, r.x2);
        addEdge(r.x12, r.x14);
        addEdge(r.x1, r.x12);
        addEdge(r.x1, r.x13);
        addEdge(r.x24, r.x4);
        addEdge(r.x14, r.x24);
        addEdge(r.x14, r.x34);
        addEdge(r.x13, r.x14);
        addEdge(r.x13, r.x3);
        addEdge(r.x34, r.x4);
        addEdge(r.x3, r.x34);
    }

    private Optional<LeftSide> orientate() {
        LeftSide left = new LeftSide();
        left.i = interiorNode;

        ENode[] es = interiorNode.getAdjacentENodes();
        if (es.length != 4) {
            return Optional.empty();
        }

        // Find x4
        // I'm assuming es contains enodes ordered clockwise.
        // x4 should be the only node to have direct eges with corner nodes. We should find x2-x4 edge first, and then x4-x3 one.
        Optional<Integer> i4Index = Optional.empty();
        for (int i = 0; i < es.length; i++) {
            if (es[i].hasEdgeBetween(es[(i + 1) % es.length]) && es[i].hasEdgeBetween(es[pythonMod(i - 1, es.length)])) {
                if (i4Index.isPresent()) {
                    return Optional.empty();
                } else {
                    i4Index = Optional.of(i);
                }
            }
        }

        int i4;
        if (!i4Index.isPresent())
            return Optional.empty();
        else
            i4 = i4Index.get();

        left.x1 = es[(i4 + 2) % es.length];
        left.x2 = es[(i4 + 3) % es.length];
        left.x3 = es[(i4 + 1) % es.length];
        left.x4 = es[i4];

        // find midpoint nodes
        List<Optional<ENode>> ms = Arrays.asList(
                getMidpoint(left.x1, left.x2),
                getMidpoint(left.x1, left.x3)
        );
        for (Optional<ENode> m : ms) {
            if (!m.isPresent()) {
                return Optional.empty();
            }
        }
        left.x12 = ms.get(0).get();
        left.x13 = ms.get(1).get();

        // Make sure there are no additional edges
        int edgeCount = 0;
        List<GraphNode> ns = Arrays.asList(left.i, left.x1, left.x12, left.x2, left.x13, left.x3, left.x4);
        for (int i = 0; i < ns.size() - 1; ++i) {
            for (int j = i + 1; j < ns.size(); ++j) {
                if (ns.get(i).hasEdgeBetween(ns.get(j))) {
                    edgeCount++;
                }
            }
        }
        if (edgeCount != 10) {
            return Optional.empty();
        }

        return Optional.of(left);
    }

    private int pythonMod(int a, int b) {
        int res = a % b;
        return res < 0 ? a + b : a;
    }

    private Coordinates mid(GraphNode a, GraphNode b) {
        return a.getCoordinates().middlePoint(b.getCoordinates());
    }

    private Optional<ENode> getMidpoint(GraphNode a, GraphNode b) {
        List<ENode> candidates = Arrays.stream(a.getAdjacentENodes())
                .filter(n -> n.getCoordinates().equals(mid(a, b)) && n.hasEdgeBetween(b) && n.hasEdgeBetween(a)) // might need to remove second edge check
                .collect(Collectors.toList());
        if (candidates.size() < 1) {
            return Optional.empty();
        }
        // In case there's more than 1 possible node, pick the first one.
        return Optional.ofNullable(candidates.get(0));
    }

    private ENode pushDown(GraphNode n, String name) {
        Coordinates c = Coordinates.createCoordinatesWithOffset(n.getXCoordinate(), n.getYCoordinate(), n.getLevel() + 1);
        return new ENode(graphModel, getNodeName(interiorNode, name), c);
    }

    private void addEdge(GraphNode a, GraphNode b) {
        graphModel.insertGraphEdge(getEdgeName(a, b), a, b);
        if (a instanceof ENode) {
            b.addNeighbourENode(a);
        }
        if (b instanceof ENode) {
            a.addNeighbourENode(b);
        }
    }
}


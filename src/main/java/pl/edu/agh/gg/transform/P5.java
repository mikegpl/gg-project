package pl.edu.agh.gg.transform;

import pl.edu.agh.gg.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P5 extends Transformation {

    private static final int REQUIRED_EDGE_COUNT = 9;

    protected Optional<LeftEmbedding> leftCache;
    protected Optional<RightEmbedding> rightCache;
    private GraphModel graphModel;
    private InteriorNode interiorNode;

    @Override
    public boolean isApplicable(GraphModel graphModel, GraphNode interiorNode, boolean ignored) {
        this.graphModel = graphModel;
        if (!(interiorNode instanceof InteriorNode)) {
            return false;
        }
        this.interiorNode = (InteriorNode) interiorNode;
        this.leftCache = getLeftEmbedding();

        return leftCache.isPresent();
        //return true;
    }

    @Override
    public void transform(GraphModel graphModel, GraphNode interiorNode, boolean ignored) {
        if (this.graphModel != graphModel || this.interiorNode != interiorNode) {
            throw new IllegalStateException("Inconsistent arguments of .isApplicable() and .transform()");
        }
        LeftEmbedding l = leftCache.orElseThrow(() ->
                new IllegalStateException(".isApplicable() must return true before calling .transform()"));

        // Change symbol to lowercase
        l.i.symbolToLowerCase();
        //System.out.println(l.x1.getId());

        RightEmbedding r = new RightEmbedding();
        rightCache = Optional.of(r);

        // Push down nodes from the previous level
        r.x1 = pushDown(l.x1, "e1");
        r.x2 = pushDown(l.x2, "e2");
        r.x13 = pushDown(l.x13, "e13");
        r.x3 = pushDown(l.x3, "e3");
        r.x4 = pushDown(l.x4, "e4");

        // Create new edge nodes
        r.x14 = pushDown(l.i, "e14");
        r.x34 = new ENode(graphModel, getNodeName(l.i, "e34"), mid(r.x3, r.x4));
        r.x12 = new ENode(graphModel, getNodeName(l.i, "e12"), mid(r.x1, r.x2));
        r.x24 = new ENode(graphModel, getNodeName(l.i, "e24"), mid(r.x2, r.x4));

        // Create new interiors
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

    private Optional<LeftEmbedding> getLeftEmbedding() {
        if (!interiorNode.isSymbolUpperCase()) {
            return Optional.empty();
        }

        LeftEmbedding e = new LeftEmbedding();
        e.i = interiorNode;

        ENode[] es = Arrays.stream(interiorNode.getAdjacentENodes())
                .filter(n -> n instanceof ENode)
                .map(n -> (ENode) n)
                .toArray(ENode[]::new);
        if (interiorNode.getAdjacentENodes().length != 4 || es.length != 4) {
            return Optional.empty();
        }


        int i4 = -1;
        for (int i = 0; i < es.length; ++i) {
            if (!(es[i].hasEdgeBetween(es[(i + 1) % es.length]))) {
                if (i4 >= 0) {
                    return Optional.empty();
                } else {
                    i4 = i;
                }
            }
        }
        e.x4 = es[(i4 + 3) % es.length];
        e.x3 = es[(i4) % es.length];
        e.x1 = es[(i4 + 1) % es.length];
        e.x2 = es[(i4 + 2) % es.length];

//        System.out.println(i4);
//        System.out.println(e.x1.getId());
//        System.out.println(e.x2.getId());
//        System.out.println(e.x3.getId());
//        System.out.println(e.x4.getId());

        // find midpoint nodes
        List<Optional<ENode>> ms = Arrays.asList(
                getMidpoint(e.x1, e.x3)
        );



        for (Optional<ENode> m : ms) {
            if (!m.isPresent()) {

                return Optional.empty();
            }
        }
        //e.x12 = ms.get(0).orElseThrow(() -> new NoSuchElementException("impossible"));
        e.x13 = ms.get(0).orElseThrow(() -> new NoSuchElementException("impossible"));
        //e.x24 = ms.get(2).orElseThrow(() -> new NoSuchElementException("impossible"));

        // Make sure there are no additional edges. We know that the ones required are present, so count all edges
        // between points in the embedding and compare the counts.
        int edgeCount = 0;
        List<GraphNode> ns = Arrays.asList(e.i, e.x1, e.x2, e.x13, e.x3, e.x4);
        for (int i = 0; i < ns.size() - 1; ++i) {
            for (int j = i + 1; j < ns.size(); ++j) {
                if (ns.get(i).hasEdgeBetween(ns.get(j))) {
                    edgeCount++;
                }
            }
        }
        if (edgeCount != REQUIRED_EDGE_COUNT) {
            return Optional.empty();
        }

        return Optional.of(e);
    }

    private Coordinates mid(GraphNode a, GraphNode b) {
        return a.getCoordinates().middlePoint(b.getCoordinates());
    }

    private Optional<ENode> getMidpoint(GraphNode a, GraphNode b) {
        List<ENode> candidates = Arrays.stream(a.getAdjacentENodes())
                .filter(n -> n.getCoordinates().equals(mid(a, b)) && n.hasEdgeBetween(b))
                .filter(n -> n instanceof ENode)
                .map(n -> (ENode) n)
                .collect(Collectors.toList());



        if (candidates.size() < 1) {
            //System.out.println(candidates);
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

    private static final class LeftEmbedding {
        InteriorNode i;
        ENode x1;
        ENode x2;
        ENode x13;
        ENode x3;
        ENode x4;
    }

    private static final class RightEmbedding {
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
}
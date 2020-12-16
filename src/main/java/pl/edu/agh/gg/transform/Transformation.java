package pl.edu.agh.gg.transform;

import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;

public abstract class Transformation {

    public abstract boolean isApplicable(GraphModel graph, GraphNode interior);

    public abstract void transform(GraphModel graph, GraphNode interior);

    protected String getNodeName(GraphNode graphNode, String nodeName) {
        return graphNode.getId() + nodeName;
    }

    protected String getEdgeName(GraphNode first, GraphNode second) {
        return first.getId() + second.getId();

    }

}

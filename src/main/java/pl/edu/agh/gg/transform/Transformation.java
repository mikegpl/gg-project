package pl.edu.agh.gg.transform;

import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;

public abstract class Transformation {

    public abstract boolean isApplicable(GraphModel graphModel, GraphNode interiorNode, boolean isHorizontal);

    public abstract void transform(GraphModel graphModel, GraphNode interiorNode, boolean isHorizontal);

    protected String getNodeName(GraphNode graphNode, String nodeName) {
        return graphNode.getId() + nodeName;
    }

    protected String getEdgeName(GraphNode first, GraphNode second) {
        return first.getId() + second.getId();

    }

}

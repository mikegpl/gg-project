package pl.edu.agh.gg.transform;

import pl.edu.agh.gg.model.*;

public interface TransformationMerge {

    boolean isApplicable(GraphModel graph, GraphNode interiorNode1, GraphNode interiorNode2, boolean isHorizontal);

    void transform(GraphModel graph, GraphNode interiorNode1, GraphNode interiorNode2, boolean isHorizontal);

}

package com.edu.agh.gg.transform;

import com.edu.agh.gg.model.*;

public interface TransformationMerge {

    boolean isApplicable(GraphModel graph, GraphNode interior);

    void transform(GraphModel graph, InteriorNode interior1, GraphNode interior2);

}

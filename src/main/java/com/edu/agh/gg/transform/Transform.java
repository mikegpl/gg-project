package com.edu.agh.gg.transform;

import com.edu.agh.gg.model.GraphModel;
import com.edu.agh.gg.model.GraphNode;

public interface Transform {

    void transform(GraphModel graph, GraphNode node);
}

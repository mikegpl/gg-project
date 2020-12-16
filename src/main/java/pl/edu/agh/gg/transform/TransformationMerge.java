package pl.edu.agh.gg.transform;

import pl.edu.agh.gg.model.GraphModel;
import pl.edu.agh.gg.model.GraphNode;
import pl.edu.agh.gg.model.InteriorNode;

public interface TransformationMerge {

    boolean isApplicable(GraphModel graph, GraphNode interior);

    void transform(GraphModel graph, InteriorNode interior1, GraphNode interior2);

}

package pl.edu.agh.gg.visualization;

import pl.edu.agh.gg.model.GraphModel;

public class Visualizer {
    private GraphModel graph;

    public Visualizer(GraphModel graph) {
        this.graph = graph;
    }

    public void visualize() {
        graph.display();
    }

    public void visualize(double level) {
        graph.getGraphModel(level).display();
    }

    public void visualizeUpTo(double level) {
        graph.getGraphModelUpTo(level).display();
    }
}

package pl.edu.agh.gg.model;

import java.util.Objects;

public final class Coordinates {
    private final double x;
    private final double y;
    private final double level;

    public Coordinates(double x, double y, double level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getLevel() {
        return level;
    }

    public double distance(Coordinates rhs) {
        return Coordinates.distance(this, rhs);
    }

    public static double distance(Coordinates p1, Coordinates p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY()- p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Coordinates middlePoint(Coordinates rhs) {
        return Coordinates.middlePoint(this, rhs);
    }

    public static Coordinates middlePoint(Coordinates p1, Coordinates p2) {
        double xs = p1.getX() + p2.getX();
        double ys = p1.getY() + p2.getY();
        return new Coordinates(xs / 2d, ys / 2d, p1.level);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates coordinates = (Coordinates) o;
        return Double.compare(coordinates.x, x) == 0 &&
                Double.compare(coordinates.y, y) == 0 &&
                coordinates.level == level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, level);
    }
}

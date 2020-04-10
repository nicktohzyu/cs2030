public class Point {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return String.format("point (%.3f, %.3f)", x, y);
    }

    public Point midPoint(Point p) {
        return new Point((x + p.x) / 2, (y + p.y) / 2);
    }

    public double angleTo(Point p) {
        return Math.atan2((p.y - y), (p.x - x));
    }

    public double distanceTo(Point p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point moveTo(double a, double d) {
        return new Point(x + d * Math.cos(a), y + d * Math.sin(a));
    }
}


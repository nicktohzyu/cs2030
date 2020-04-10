public class Point {
    double x, y;

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("point (%.3f, %.3f)", x, y);
    }

    public Point midPoint(Point other){
        return new Point((x+other.x)/2, (y+other.y)/2);
    }

    public double angleTo(Point other){
        return Math.atan2((other.y-y), (other.x-x));
    }

    public double distanceTo(Point p) {
        double dx = x - p.x;
        double dy = y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point moveTo(double angle, double d){
        return new Point(x + d*Math.cos(angle), y+d*Math.sin(angle));
    }
}

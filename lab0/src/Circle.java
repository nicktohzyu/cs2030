public class Circle {
    private Point center;
    private double radius;

    private Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }

    public static Circle getCircle(Point center, double radius){
        if(radius<=0){
            return null;
        }
        return new Circle(center, radius);
    }

    public String toString(){
        return String.format("circle of radius %.1f centered at %s", radius, center.toString());
    }
}

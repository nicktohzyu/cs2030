import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point p1 = new Point(sc.nextDouble(), sc.nextDouble());
        Point p2 = new Point(sc.nextDouble(), sc.nextDouble());
        double radius = sc.nextDouble();
        Circle c = createCircle(p1, p2, radius);
        if(c == null){
            System.out.println("No valid circle can be created");
        } else {
            System.out.println("Created: " + c.toString());
        }
    }

    public static Circle createCircle(Point p, Point q, double r) {
        Point m = p.midPoint(q);
        //System.out.println("point m: " + m);
        double qm = q.distanceTo(m);
        //System.out.println("distance qm: " + qm);
        if (qm > r || qm <= 0) {
            return null;
        }
        double mc = Math.sqrt(r * r - qm * qm);
        //System.out.println("distance mc: " + mc);
        double anglemc = q.angleTo(m) - 0.5 * Math.PI;
        //System.out.println("angle mc: " + anglemc);
        Point c = m.moveTo(anglemc, mc);
        //System.out.println("point c: " + c);
        return Circle.getCircle(c, r);
    }
}

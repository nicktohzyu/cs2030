import java.util.Scanner;

public class Main{

    public static Circle createCircle(Point p, Point q, double r){
        Point m = p.midPoint(q);
        //System.out.println("point m: " + m);
        double qm = q.distanceTo(m);
        //System.out.println("distance qm: " + qm);
        if(qm>r || qm <= 0){
            return null;
        }
        double mc = Math.sqrt(r*r-qm*qm);
        //System.out.println("distance mc: " + mc);
        double anglemc = q.angleTo(m)-0.5*Math.PI;
        //System.out.println("angle mc: " + anglemc);
        Point c = m.moveTo(anglemc, mc);
        //System.out.println("point c: " + c);
        return Circle.getCircle(c, r);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt(), ans = 0;
        Point[] points = new Point[n];
        Circle temp;

        for (int i = 0; i < n; i++) {
            points[i] = new Point(s.nextDouble(), s.nextDouble());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp = createCircle(points[i], points[j], 1);
                if(temp != null){
                    ans = Math.max(ans, temp.countPoints(points));
                }
                //dn = createCircle(pointArr[j], pointArr[i], 1);
            }
        }

        System.out.printf("Maximum Disc Coverage: %d\n", ans);
    }
}

import java.util.Random;
public class Point {
    private double x,y;

    public Point (Rectangle r) {
        this.x = Math.random() * r.getWidth() + r.getX1();
        this.y = Math.random() * r.getHeight() + r.getY1();
    }

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public String toString() {
        return "(" + Double.toString(this.x) + ", " + Double.toString(this.y) + ")";
    }
}
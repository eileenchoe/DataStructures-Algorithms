public class Circle extends Shape{
    private Point center;
    private double radius;

    public Circle () {
    }

    public Circle (Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Circle (double x, double y, double radius) {
        this.center = new Point(x,y);
        this.radius = radius;
    }

    public String toString() {
        return "Circle with center: " + this.center.toString() + " " + "and radius: " + Double.toString(radius);
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }
    
    public Point getMax() {
        return center;
    }

    public Point getMin() {
        return center;
    }

    public Rectangle getRectangle() {
        Point lower = new Point(center.getX() - this.radius, center.getY() - this.radius);
        Point upper = new Point(center.getX() + this.radius, center.getY() + this.radius);
        return new Rectangle(lower,upper);
    }

    public boolean inCircle(Point p) {
        return Math.pow(p.getX() - this.center.getX(), 2) + Math.pow(p.getY() - this.center.getY(), 2) < Math.pow(this.radius, 2);
    }
}
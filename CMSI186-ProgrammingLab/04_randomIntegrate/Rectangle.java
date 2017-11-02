public class Rectangle extends Shape{
    private double x1, x2, y1, y2;

    //default rectangle
    public Rectangle() {
        this(new Point(0,0), new Point(1,1));
    }

    public Rectangle (double x1, double x2, double y1, double y2) {
        //bottom left, top right
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public Rectangle (Point p1, Point p2) {
        this.x1 = p1.getX();
        this.y1 = p1.getY();
        this.x2 = p2.getX();
        this.y2 = p2.getY();

    }

    public double getX1() {
        return this.x1;
    }

    public double getX2() {
        return this.x2;
    }
    public double getY1() {
        return this.y1;
    }

    public double getY2() {
        return this.y2;
    }

    public double getWidth() {
        return Math.abs(this.x2 - this.x1);
    }

    public double getHeight() {
        return Math.abs(this.y2 - this.y1);
    }

    public double getArea() {
        return Math.abs(this.x2 - this.x1) * Math.abs(this.y2 - this.y1);
    }

    public String toString() {
        return "The two corners are: " + this.getX1() + " " + this.getY1() + " " + this.getX2() + " " + this.getY2(); 
    }

}
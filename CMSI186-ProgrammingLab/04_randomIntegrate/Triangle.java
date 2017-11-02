public class Triangle extends Shape{
    
    private Point a, b, c;
    private double area;

    public Triangle() {
    }

    public Triangle (Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.area = Math.abs( (a.getX() * (b.getY()-c.getY()) + b.getX() * (c.getY()-a.getY()) + c.getX() * (a.getY()-b.getY()) ) / 2.0);
    }

    public Triangle (double x1, double y1, double x2, double y2, double x3, double y3) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
        this.c = new Point(x3, y3);
        this.area = Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
    }

    public String toString() {
        return "Triangle with points: " + this.a.toString() + " " + this.b.toString() + " " + this.c.toString();
    }

    public double getArea() {
        return area;
    }

    public Point getA() {
        return a;
    }
    public Point getB() {
        return b;
    }
    public Point getC() {
        return c;
    }

    public Rectangle getRectangle() {
        double[] xvalues = new double[3];
        double[] yvalues = new double[3];

        double minX = 0.0;
        double minY = 0.0;
        double maxX = 0.0;
        double maxY = 0.0;

        xvalues[0] = this.a.getX();
        yvalues[0] = this.a.getY();
        xvalues[1] = this.b.getX();
        yvalues[1] = this.b.getY();
        xvalues[2] = this.c.getX();
        yvalues[2] = this.c.getY();

        for (int i = 0; i < 3; i ++) {
            minX = xvalues[i] < minX ? xvalues[i] : minX;
            maxX = xvalues[i] > minX ? xvalues[i] : maxX;
            minY = yvalues[i] < minY ? yvalues[i] : minY;
            maxY = yvalues[i] > maxY ? yvalues[i] : maxY;
        }

        Point min = new Point(minX,minY);
        Point max = new Point(maxX,maxY);

        return new Rectangle(min,max);
    }

    public boolean inTriangle(Point p) {
        Triangle t1 = new Triangle(this.a,this.b,p);
        double area1 = t1.getArea();
        Triangle t2 = new Triangle(this.a,p,this.c);
        double area2 = t2.getArea();
        Triangle t3 = new Triangle(p,this.b,this.c);
        double area3 = t3.getArea();

        return (this.area < (area1 + area2 + area3) * 1.00000000001 && this.area > (area1+area2+area3) * .999999999999999);
    }
}
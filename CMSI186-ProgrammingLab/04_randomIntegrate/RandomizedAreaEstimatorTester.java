public class RandomizedAreaEstimatorTester {

    public static void main (String[] args) {
        example1();
        example2();
    }

    public static void example1() {
        System.out.println("\nEXAMPLE 1 OUTPUT STATEMENTS ARE:\n");
        RandomizedAreaEstimator re = new RandomizedAreaEstimator();
        Circle c1 = new Circle(new Point(0,0), 1);
        Triangle t = new Triangle(
            new Point(0,0),
            new Point(2,0),
            new Point(2,2)
        );

        Circle c2 = new Circle (new Point(0,0) ,2);
        Shape[] shapeArray = new Shape[]{ c1, t, c2};
        re.calculations(shapeArray);

        System.out.println("\n**CHECK** Area of circle ~ 3.14159265359");
        System.out.println("**CHECK** Area of triangle ~ 2");
        System.out.println("**CHECK** Area of circle ~ 12.5663706144");
        System.out.println("**CHECK** Area of union ~ 12.9955742876");
        System.out.println("**CHECK** Area of intersection ~ 0.39269908169");
    }

    public static void example2() {
        System.out.println("\nEXAMPLE 2 OUTPUT STATEMENTS ARE: \n");
        RandomizedAreaEstimator re = new RandomizedAreaEstimator();
        Circle c = new Circle(new Point(0,0), 0.5);
        Triangle t = new Triangle (
            new Point(-1,-1),
            new Point(0,0),
            new Point(1,-1)
        );
        Shape[] shapeArray = new Shape[] {c, t};
        re.calculations(shapeArray);

        System.out.println("\n**CHECK** Area of circle ~ 0.78539816339");
        System.out.println("**CHECK** Area of triangle ~ 1");
        System.out.println("**CHECK** Area of union ~ 1.58904862255");
        System.out.println("**CHECK** Area of intersection ~ 0.19634954084");
    }
}
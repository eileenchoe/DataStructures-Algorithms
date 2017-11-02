import java.util.Arrays;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RandomizedAreaEstimator {

    public static Shape[] getShapes (String[]args) {
        args = (editShapeNames(args));
        List<Integer> shapeArrayIndex = new ArrayList<>();
        for (int i = 0; i < args.length; i ++) {
            try{
                 Double.parseDouble(args[i]);
            } catch (Exception e){
                if (args[i].toLowerCase().equals("circle") || args[i].toLowerCase().equals("triangle")) {
                    shapeArrayIndex.add(i);
                } else {
                    throw new IllegalArgumentException("Not a shape in Shape class");
                }
            }
        }
        Shape[] shapeArray = new Shape[shapeArrayIndex.size()];
        for (int i = 0; i < shapeArrayIndex.size(); i++) {
            try{
                //circle constructor
                if(Class.forName(args[shapeArrayIndex.get(i)]).newInstance() instanceof Circle) {
                    double[] circleParameters = new double[3];
                    for (int j = 1; j <= 3; j++) {
                        try {
                            circleParameters[j-1] = Double.parseDouble(args[shapeArrayIndex.get(i) + j]);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Syntax: circle x y r");
                        }
                    }
                    shapeArray[i] = new Circle(circleParameters[0], circleParameters[1], circleParameters[2]);
                //triangle constructor
                } else if (Class.forName(args[shapeArrayIndex.get(i)]).newInstance() instanceof Triangle) {
                    double [] triangleParameters = new double[6];
                    for(int j = 1; j <= 6; j ++) {
                        try{
                            triangleParameters[j-1] = Double.parseDouble(args[shapeArrayIndex.get(i)+j]);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Syntax: triangle x1 y1 x2 y2 x3 y3");
                        }
                    }
                    shapeArray[i] = new Triangle(triangleParameters[0], triangleParameters[1],triangleParameters[2],
                                                 triangleParameters[3],triangleParameters[4],triangleParameters[5]);
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        return shapeArray;
    }

    public static String[] editShapeNames (String[] args) {
        for (int i = 0; i < args.length; i ++)
        try {
            String fstr = args[i].substring(0,1).toUpperCase() + args[i].substring(1,args[i].length());
            args[i] = fstr;
        } catch (Exception e) {}
        return args;
    }

    public static Rectangle boundingBox(Shape shape) {
        if(shape instanceof Circle){
            return ((Circle)shape).getRectangle();
        } else if (shape instanceof
         Triangle) {
            return ((Triangle)shape).getRectangle();
        } else {
            throw new IllegalArgumentException("input error");
        }
    }

    public static Rectangle getBoundingBox(Shape[] shapes) {
        Rectangle[] boundingBoxes = new Rectangle[shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            boundingBoxes[i] = boundingBox(shapes[i]);
        }
        return maxBox(boundingBoxes);
    }

    public static Rectangle maxBox (Rectangle[] r) {
        double minX = r[0].getX1();
        double maxX = r[0].getX2();
        double minY = r[0].getY1();
        double maxY = r[0].getY2();
        for (int i = 1; i < r.length; i++) {
            minX = r[i].getX1() < minX ? r[i].getX1() : minX;
            maxX = r[i].getX2() > maxX ? r[i].getX2() : maxX;
            minY = r[i].getY1() < minY ? r[i].getY1() : minY;
            maxY = r[i].getY2() > maxY ? r[i].getY2() : maxY;            
        }
        return new Rectangle(new Point(minX,minY), new Point(maxX,maxY));
    }

    public static void calculations (Shape[] shapes) {
        Rectangle r = getBoundingBox(shapes);
        double rectArea = r.getArea();
        int unionP = 0;
        int intersectP = 0;
        double totalP = 1000000.0;
        int [] insidePArray = new int[shapes.length];
        int [] intersectHelper = new int[shapes.length];
        int nonIntersectP = 0;
        double [] areasArray = new double[shapes.length];
        
        for (int k = 0; k < totalP; k++) {
            Point p = new Point (r);
            int[] helper = new int[shapes.length];
            for (int i = 0; i < shapes.length; i ++) {
                if(shapes[i] instanceof Circle){
                    helper[i] = (((Circle)shapes[i]).inCircle(p)) ? 1 : 0; 
                } else if (shapes[i] instanceof Triangle) {
                    helper[i] = (((Triangle)shapes[i]).inTriangle(p)) ? 1 : 0; 
                }
            }
            int sum = 0;
            for (int j = 0; j < shapes.length; j++) {
                sum += helper[j];
                insidePArray[j] += helper[j];
            }
            if (sum > 0) {
                unionP += 1;
            }
            if (sum == 1) {
                nonIntersectP += 1;
            }
            intersectP += (sum == shapes.length) ? 1 : 0;
        }

        for (int p = 0; p < shapes.length; p++) {
            areasArray[p] = (double) ((insidePArray[p] / totalP)) * rectArea;
            System.out.println(shapes[p].toString() + " " + "has area of: " + areasArray[p]);
        }

        double union = (((double)unionP / totalP)) * rectArea;
        double intersection = (((double)intersectP / totalP)) * rectArea;
        double nonIntersection = ((double)nonIntersectP / totalP) * rectArea;

        System.out.println("The Union is: " + union);
        System.out.println("The Intersection is: " + intersection);
        System.out.println("Area of Non Intersection is: " + nonIntersection);

    }

    public static void main(String[] args) {
        Shape[] array = getShapes(args);
        calculations(array);
    }
}
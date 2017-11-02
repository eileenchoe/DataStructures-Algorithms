import java.util.Random;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class RandomizedIntegrator {

    // Available SimpleFunctions: Sin, Cos, Exp, Log, Poly, Sqrt, CosXTimesCos2X 

    private static SimpleFunction getFunction (Class<?> c, String[]args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        try {
            Constructor constructor = c.getConstructor(double[].class);
            double[] additionalArguments = new double[args.length-3];
            for (int i = 1; i < args.length-2; i++) {
                additionalArguments[i-1] = Double.parseDouble(args[i]);
            }
            return (SimpleFunction)constructor.newInstance(additionalArguments);
        } catch (NoSuchMethodException e) {
            Constructor constructor = c.getConstructor();
            return (SimpleFunction)constructor.newInstance();
        }
    }

    public static void main (String[] args) {
        try {
            if(args.length < 3) {
                throw new ArrayIndexOutOfBoundsException("Need more inputs");
            } 
            String fstr = args[0].substring(0,1).toUpperCase() + args[0].substring(1,args[0].length());
            SimpleFunction function = getFunction(Class.forName(fstr), args);
            double bound1 = Double.parseDouble(args[args.length - 2]);
            double bound2 = Double.parseDouble(args[args.length - 1]);
           
           double area;
            if (bound2 < bound1) {
                double save = bound2;
                bound2 = bound1;
                bound1 = save;
                area = (-1) * area(function, bound1, bound2);
            } else {
                area = area(function, bound1, bound2);
            }
            System.out.println(area);

        } catch (ClassNotFoundException e) {
            System.err.println(args[0] + " is not defined");
        } catch (NoSuchMethodException e) {
            System.err.println("Can't find a constructor to make your function");
        } catch (ClassCastException e) {
            System.err.println(args[0] + " does not implement SimpleFunction");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println(args[0] + " is not public or is malformed");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Use syntax: java RandomizedIntegrator function name additionalarguments parameters");
        }
    }

    public static Rectangle boundBoxHeight (SimpleFunction function, double bound1, double bound2) {
        SimpleFunctionEvaluator evaluator = new SimpleFunctionEvaluator();
        double test = bound1;
        double high = 0;
        double low = 0;

        while (test < bound2) {
            high = Math.abs(evaluator.calculate(function, test)) > high ? Math.abs(evaluator.calculate(function, test)) : high;
            test += 0.01;
        }
        return new Rectangle(bound1, bound2, 0.0, Math.ceil(high));    
    }

    public static double area (SimpleFunction function, double bound1, double bound2) {
        SimpleFunctionEvaluator evaluator = new SimpleFunctionEvaluator();
        double insidePointsPositive = 0.0;
        double insidePointsNegative = 0.0;
        double totalPoints = 1000000.0;
        double save = bound1;

        if (bound1 > bound2) {
            bound1 = bound2;
            bound2 = save;
        }

        Rectangle r = boundBoxHeight(function, bound1, bound2);

        for (int i = 0; i < totalPoints; i++) {
            Point point = new Point (r);
            if(evaluator.calculate(function, point.getX()) > 0 && point.getY() < evaluator.calculate(function, point.getX())) {
                insidePointsPositive ++;
            } else if (evaluator.calculate(function, point.getX()) < 0 && point.getY() < Math.abs(evaluator.calculate(function, point.getX()))) {
                insidePointsNegative ++;
            }
        } 
        double totalInsidePoints = insidePointsPositive - insidePointsNegative;
        return (totalInsidePoints / totalPoints) * r.getArea();
    }

}
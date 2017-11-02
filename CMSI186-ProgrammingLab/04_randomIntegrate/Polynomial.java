import java.util.stream.IntStream;
public class Poly implements SimpleFunction {

    private double[] coefficients;

    public Poly(double... coefficients) {
        this.coefficients = coefficients;

    }

    @Override public double eval(double x) {
        return IntStream.range(0, coefficients.length)
            .mapToDouble(e -> coefficients[e] * Math.pow(x, e))
            .sum();
    }
}

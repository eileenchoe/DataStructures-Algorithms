public class SqrtOfOnePlusCosX implements SimpleFunction {
    @Override public double eval(double x) {
        return Math.sqrt(1 + Math.cos(x));
    }
}
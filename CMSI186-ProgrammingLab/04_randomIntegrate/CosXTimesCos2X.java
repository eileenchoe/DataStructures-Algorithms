public class CosXTimesCos2X implements SimpleFunction {
    @Override public double eval(double x) {
        return (Math.cos(x) * Math.cos(2 * x)) ;
    }
}
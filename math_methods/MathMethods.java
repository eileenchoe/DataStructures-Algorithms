import java.math.BigInteger;
import java.util.Arrays;

public class MathMethods {

    public MathMethods() {

    };

    public static java.math.BigInteger factorial (int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        if (n == 0) { return BigInteger.ONE; }
        BigInteger bigInt = BigInteger.valueOf(n);
        for (int i = n - 1; i > 0; i--) {
            bigInt = bigInt.multiply(BigInteger.valueOf(i));
        }
        return bigInt;
    }

    public static java.math.BigInteger fibonacci (int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        if (n < 2) { return BigInteger.valueOf(n); }
        BigInteger prev = BigInteger.valueOf(0);
        BigInteger next = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            BigInteger store = prev.add(next);
            prev = next;
            next = store;
        }
        return next;
    }

    public static long gcd (long m, long n) {
        if (m == 0 || n == 0) { throw new IllegalArgumentException(); }
        long rem = m % n;
        if (rem == 0) { return Math.abs(n); }
        return gcd (n, rem);
    }

    public static long lcm (long m, long n) {
        if (m == 0 || n == 0) { return 0; }
        return Math.abs(m * n) / gcd(m,n);
    }

    public static double poly(double x, double[] coeff) {
        if (coeff.length == 0) { throw new IllegalArgumentException(); }
        double answer = 0.0;
        for (int i = coeff.length - 1; i >= 0; i--) {
            answer = coeff[i] + (x * answer);
        }
        return answer;
    }

    public static double sqrt(double x, double epsilon) {
        if (x < 0) { throw new IllegalArgumentException(); }
        return root(2, x, epsilon);
    }

    public static double root (int n, double x, double epsilon) {
        if (epsilon < 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            // 0 roots are impossible
            throw new IllegalArgumentException();
        } else if (n % 2 == 0 && x < 0) {
            // even root of a negative number is impossible
            throw new IllegalArgumentException();
        }

        if (x < 0) {
            if (n > 0) {
                if (x > -1 && x < 0) {
                    return -1.0 * rootHelper(n, Math.abs(x), epsilon, 0, 1 / Math.abs(x));
                }
                return -1.0 * rootHelper(n, Math.abs(x), epsilon, 0, Math.abs(x));
            } else {
                if (x > -1 && x < 0) {
                    return -1.0 * rootHelper(n, Math.abs(x), epsilon, 0, 1 /Math.abs(x));
                }
                return -1.0 * rootHelper(n, Math.abs(x), epsilon, 0, 1);
            }
        }
        return (x < 1 && x > 0) ? rootHelper(n, x, epsilon, 0, 1 / x) : rootHelper(n, x, epsilon, 0, x);
    }


    private static double rootHelper (int n, double x, double epsilon, double lowerB, double upperB) {
        double guess = ((upperB - lowerB) / 2) + lowerB;
        double guessValue = MathMethods.power(guess, n);
        if (guessValue == x || (upperB - lowerB) <= epsilon) {
            return guess;
        }
        if (n >= 0) {
            return guessValue > x ? rootHelper(n, x, epsilon, lowerB, guess) : rootHelper(n, x, epsilon, guess, upperB);
        }
        else {
            return guessValue < x ? rootHelper(n, x, epsilon, lowerB, guess) : rootHelper(n, x, epsilon, guess, upperB);
        }
    }

    public static double power (double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        } else if (n == -1) {
            return 1 / x;
        }
        if (n % 2 != 0) {
            double save = n > 0 ? power (x, (n - 1) / 2) : power (x, (n + 1) / 2);
            return (n > 0 ? x * save * save : 1 / x * save * save);
        } else {
            double save = power (x, n / 2);
            return save * save;
        }
    }

    public static void main (java.lang.String[] args) {
        String method = args[0].toLowerCase();
        String[] argsArr = Arrays.copyOfRange(args, 1, args.length);
        try {
            switch (method) {
                case "factorial":
                    Integer factInput = Integer.parseInt(argsArr[0]);
                    System.out.println(factorial(factInput));
                    break;
                case "fibonacci":
                    Integer fibInput = Integer.parseInt(argsArr[0]);
                    System.out.println(fibonacci(fibInput));
                    break;
                case "gcd":
                    Long gcdInputA = Long.parseLong(argsArr[0]);
                    Long gcdInputB = Long.parseLong(argsArr[1]);
                    System.out.println(gcd(gcdInputA, gcdInputB));
                    break;
                case "lcm":
                    System.out.println(lcm(Long.parseLong(argsArr[0]), Long.parseLong(argsArr[1])));
                    break;
                case "poly":
                    double[] coeffs = new double[argsArr.length - 1];
                    for (int i = 0; i < argsArr.length - 1; i++) {
                        coeffs[i] = Double.parseDouble(argsArr[i + 1]);
                    }
                    System.out.println(poly(Double.parseDouble(argsArr[0]), coeffs));
                    break;
                case "sqrt":
                    double xSqrt = Double.parseDouble(argsArr[0]);
                    System.out.println(sqrt(xSqrt, Double.parseDouble(argsArr[1])));
                    break;
                case "root":
                    int nRoot = Integer.parseInt(argsArr[0]);
                    double xRoot = Double.parseDouble(argsArr[1]);
                    System.out.println(
                    root(nRoot, xRoot, Double.parseDouble(argsArr[2])));
                    break;
                case "power":
                    System.out.println(power(
                        Double.parseDouble(argsArr[0]),
                        Integer.parseInt(argsArr[1])
                    ));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}

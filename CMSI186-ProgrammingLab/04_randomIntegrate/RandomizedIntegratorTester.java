public class RandomizedIntegratorTester {

    public static void main(String[]args) {
        RandomizedIntegrator integrator = new RandomizedIntegrator();
        test_simpleFunctions();
        test_Sin();
        test_Cos();
        test_Exp();
        test_Log();
        test_Polynomial();
    }

    public static void test_simpleFunctions () {
        try {
            RandomizedIntegrator integrator = new RandomizedIntegrator();
            shouldEqual(integrator.area(x -> x, -10.0, 10.0), 0);
            shouldEqual(integrator.area(x -> x * x, 0, 2.0), 8.0/3.0);
            System.out.println("Simple Function tests pass");
        } catch (Exception e) {
            System.out.println("Exception thrown when testing simple functions");
        }
    }

    public static void test_Sin() {
        try {
            RandomizedIntegrator integrator = new RandomizedIntegrator();
            shouldEqual(integrator.area(Math::sin, -1.0, 1.0), 0);
            shouldEqual(integrator.area(Math::sin, 3, -1.0), 1.5303);
            System.out.println("Sin tests pass");
        } catch (Exception e) {
            System.out.println("Exception thrown when testing Sin");
        }
    }

    public static void test_Cos() {
        try {
        RandomizedIntegrator integrator = new RandomizedIntegrator();
        shouldEqual(integrator.area(Math::cos, -1.0, 1.0), 2 * Math.sin(1));
        System.out.println("Cos tests pass");
        } catch (Exception e) {
            System.out.println("Exception thrown when testing Cos");
        }
    }

    public static void test_Exp() {
        try {
        RandomizedIntegrator integrator = new RandomizedIntegrator();
        shouldEqual(integrator.area(Math::exp, 0.0, 2.0), 6.39);
        System.out.println("Exp tests pass");
        } catch (Exception e) {
            System.out.println("Exception thrown when testing Exp");
        }

    }

    public static void test_Log() {
        try {
        RandomizedIntegrator integrator = new RandomizedIntegrator();
        shouldEqual(integrator.area(Math::log, 1.0, 5.0), 4.05);
        System.out.println("Log tests pass");
        } catch (Exception e) {
            System.out.println("Exception thrown when testing Log");
        }
    }

    public static void test_Polynomial() {
        try {
        RandomizedIntegrator integrator = new RandomizedIntegrator();
        Poly poly1 = new Poly(1,2,3,4);
        shouldEqual(integrator.area(poly1, 1.0, 5.0), 776);
        Poly poly2 = new Poly(-1,2,-3,4);
        shouldEqual(integrator.area(poly2, -1.0, 3.0), 56);
        System.out.println("Polynomial tests pass");
        } catch (Exception e) {
            System.out.println("Exception thrown when testing Polynomial");
        }

    }

    public static void shouldEqual (double expected, double actual) {
        if (Math.abs(expected - actual) > 1) {
            throw new RuntimeException("Test failed");
        }
    }

}

public class TupleTestHarness {

    private static int attempts = 0;
    private static int successes = 0;

    public static void main(String[] args) {
        attempts = 0;
        successes = 0;

        test_toString();
        test_setElement();
        test_add();
        test_equals();
        test_getElement();
        test_length();
        test_total();

        System.out.println(successes + "/" + attempts + " tests passed.");
    }

    private static void displaySuccessIfTrue(boolean value) {
        attempts++;
        successes += value ? 1 : 0;

        System.out.println(value ? "success" : "failure");
    }

    private static void displayFailure() {
        displaySuccessIfTrue(false);
    }

    public static void test_toString() {
        try {
            displaySuccessIfTrue("Impossible tuple".equals(Tuple.IMPOSSIBLE.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(Tuple.IMPOSSIBLE.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        // Only one impossible.
        Tuple fakeImpossible = new Tuple(new int[0]);
        try {
            displaySuccessIfTrue("<>".equals(fakeImpossible.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(fakeImpossible.equals(Tuple.IMPOSSIBLE));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(!fakeImpossible.isImpossible());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t0 = new Tuple(0);
            displaySuccessIfTrue("<>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t1 = new Tuple(1);
            displaySuccessIfTrue("<0>".equals(t1.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t2 = new Tuple(2);
            displaySuccessIfTrue("<0,0>".equals(t2.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t3 = new Tuple(new int[] { 3, 1, 2 });
            displaySuccessIfTrue("<3,1,2>".equals(t3.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_setElement() {
        Tuple t0 = new Tuple(new int[] { 3, 1, 2 });
        try {
            t0.setElement(0, -1);
            displaySuccessIfTrue("<-1,1,2>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            t0.setElement(1, 0);
            displaySuccessIfTrue("<-1,0,2>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            t0.setElement(2, 15);
            displaySuccessIfTrue("<-1,0,15>".equals(t0.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_add() {
        try {
            Tuple t0 = new Tuple(new int[] { -1, 0, 2 });
            Tuple t1 = new Tuple(new int[] { 3, 1, 2 });
            Tuple sum = t0.add(t1);
            displaySuccessIfTrue("<2,1,4>".equals(sum.toString()) &&
                    "<-1,0,2>".equals(t0.toString()) &&
                    "<3,1,2>".equals(t1.toString()));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_equals() {
        Tuple t0 = new Tuple(new int[] { -1, 0, 2 });
        Tuple t1 = new Tuple(new int[] { -1, 0, 2 });
        
        try {
            displaySuccessIfTrue(!t0.equals("some string object"));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(!t0.equals(new Tuple(new int[] { -1, 0 })));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(!t0.equals(new Tuple(3)));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(t0.equals(t1) & t1.equals(t0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_getElement() {
        Tuple t0 = new Tuple(new int[] { -1, 0, 2 });
        
        try {
            displaySuccessIfTrue(-1 == t0.getElement(0));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(0 == t0.getElement(1));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            displaySuccessIfTrue(2 == t0.getElement(2));
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_length() {
        try {
            Tuple t0 = new Tuple(0);
            displaySuccessIfTrue(0 == t0.length());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t1 = new Tuple(new int[] { -1, 0, 2 });
            displaySuccessIfTrue(3 == t1.length());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

    public static void test_total() {
        try {
            Tuple t0 = new Tuple(0);
            displaySuccessIfTrue(0 == t0.total());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }

        try {
            Tuple t1 = new Tuple(new int[] { -1, 0, 2 });
            displaySuccessIfTrue(1 == t1.total());
        } catch (Exception e) {
            e.printStackTrace();
            displayFailure();
        }
    }

}

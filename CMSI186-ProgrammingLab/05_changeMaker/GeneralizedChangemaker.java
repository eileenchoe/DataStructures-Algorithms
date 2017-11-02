import java.util.Arrays;
public class GeneralizedChangemaker {

    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.out.println("INSUFFICIENT DATA");
                return;
            }
            int amount = Integer.parseInt(args[0]);
            if (amount < 0) {
                System.out.println("IMPROPER AMOUNT");
                System.out.println();
                //printUsage();
                return;
            }

            String[] denominationStrings = args[1].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("IMPROPER DENOMINATION");
                    System.out.println();
                    //printUsage();
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("DUPLICATED DENOMINATION");
                        System.out.println();
                        //printUsage();
                        return;
                    }
                }
            }

            Tuple change = makeChangeWithDynamicProgramming(denominations, amount);
            if (change.isImpossible()) {
                System.out.println("IMPOSSIBLE");
            } else {
                int coinTotal = change.total();
                String message = coinTotal + " COIN" + getSimplePluralSuffix(coinTotal)+ ": ";
                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = change.getElement(i);
                    message += coinCount + " x " + denominations[i] + " cent";
                    if (i != denominations.length - 1) {
                        message += ", ";
                    }
                }
                System.out.println(message);
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.");
            System.out.println();
            //printUsage();
        }
    }

    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int amount) {
        int columns = amount;
        int rows = denominations.length;
        Tuple [][] tupleArray = new Tuple[rows][columns + 1];

        for (int k = 0; k < rows; k ++) {    
            tupleArray[k][0] = new Tuple(rows);
        }

        for (int i = 0; i < rows; i ++) {
            for (int j = 1; j <= columns; j ++) {
                Tuple t = new Tuple(rows);
                t.setElement(i, 1);
                int difference = j - sum(t, denominations);
                if (difference > 0) {
                    t = tupleArray[i][difference].isImpossible() ? Tuple.IMPOSSIBLE : t.add(tupleArray[i][difference]);
                }
                if (difference < 0) {
                    t = Tuple.IMPOSSIBLE;
                }

                tupleArray[i][j] = t;
               //check if above tuple is more efficient 
                if (i > 0 && !(tupleArray[i-1][j]).isImpossible()) {
                    if (t.isImpossible()) {
                        tupleArray[i][j] = tupleArray[i-1][j];
                    } else {
                        tupleArray[i][j] = t.total() > tupleArray[i-1][j].total() ? tupleArray[i-1][j] : t;
                    }
                } 
            }
        }
        return tupleArray[rows-1][columns];
    }

    public static int sum(Tuple t, int[] denominations) {
        int sum = 0;
        for (int i = 0; i < t.length(); i++) {
            sum = sum + t.getElement(i) * denominations[i];
        }
        return sum;
    }

    private static void printUsage() {
        System.out.println("Usage: java GeneralizedChangemaker <denominations> <amount>");
        System.out.println("  - <denominations> is a comma-separated list of denominations (no spaces)");
        System.out.println("  - <amount> is the amount for which to make change");
    }

    private static String getSimplePluralSuffix(int count) {
        return count == 1 ? "" : "S";
    }

}

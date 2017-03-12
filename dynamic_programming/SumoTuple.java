public class SumoTuple {
    private int[] data;
    private boolean isImpossible;

    // Constructors
    public SumoTuple(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = 0;
        }
        this.isImpossible = false;
    }

    // Methods
    public void setImpossibleTo(boolean flag) {
        this.isImpossible = flag;
    }

    public boolean isImpossible() {
        return this.isImpossible;
    }

    public int getElement(int i) {
        checkIndex(i);
        return data[i];
    }

    public void setElement(int i, int j) {
        checkIndex(i);
        data[i] = j;
    }

    public int length() {
        return data.length;
    }

    public int total() {
        int sum = 0;
        for (int i = 0; i < length(); i++) {
            sum = sum + getElement(i);
        }
        return sum;
    }

    public SumoTuple add(SumoTuple t) {
        if (length() != t.length()) {
            throw new IllegalArgumentException();
        }
        SumoTuple sum = new SumoTuple(length());
        for (int i = 0; i < length(); i++) {
            int add = getElement(i) + t.getElement(i);
            if (add >= 1) {
                sum.setElement(i, 1);
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        if (isImpossible()) {
            return "Impossible SumoTuple";
        }

        String result = "<";
        for (int i = 0; i < length(); i++) {
            result += (i > 0 ? "," : "") + data[i];
        }
        return result + ">";
    }

    private void checkIndex(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i >= length()) {
            throw new IllegalArgumentException();
        }
    }
}

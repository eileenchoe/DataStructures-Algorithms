import java.util.Arrays;

public class SumoSolver {
	public static void main (String args[]) {
		try {
			if (args.length % 2 == 0 || args.length < 3) { throw new IllegalArgumentException(); }
			int numItems = (args.length - 1) / 2;
			int[] costs = new int[numItems];
			int[] weights = new int[numItems];
			int maxCost = parseAndCheck(args[args.length - 1]);
			int index = 0;
			for (int i = 0; i < args.length - 1; i += 2, index ++) {
				costs[index] = parseAndCheck(args[i]);
				weights[index] = parseAndCheck(args[i + 1]);
			}
			SumoSolver sumo = new SumoSolver (costs, weights, maxCost);
			sumo.solve();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static int parseAndCheck(String str) {
		int i = Integer.parseInt(str);
		if (i <= 0 ){ throw new IllegalArgumentException(); }
		return i;
	}

	int[] costs, weights;
	int maxCost, tupleLength;
	SumoTuple [][] memo;

 	public SumoSolver (int[] costs, int[] weights, int maxCost) {
		this.costs = costs;
		this.weights = weights;
		this.maxCost = maxCost;
		this.tupleLength = costs.length;
		this.memo = new SumoTuple [costs.length][maxCost + 1];
		for (int i = 0; i < costs.length; i ++) {
			this.memo[i][0] = new SumoTuple(tupleLength);
		}
	}

	public void solve () {
		SumoTuple answer = sumoRecursion(costs.length - 1, maxCost);
		print(answer);
	}

	private SumoTuple sumoRecursion (int row, int column) {
		if (row == 0) {
			SumoTuple baseCase = new SumoTuple(this.tupleLength);
			if (this.costs[row] > column) { return baseCase; }
			baseCase.setElement(0, 1);
			memoize(baseCase, row, column);
			return baseCase;
		}

		SumoTuple a = null;
		SumoTuple itemToAdd = new SumoTuple(this.tupleLength);
		itemToAdd.setElement(row, 1);
		int newRow = row - 1;
		int newColumn = column - this.costs[row];
		boolean returnBFlag = false;
		if (newRow >= 0 && newColumn >= 0) {
			a = inMemo(newRow, newColumn) ? getFromMemo(newRow, newColumn) : sumoRecursion(newRow, newColumn);
			a = a.add(itemToAdd);
			if (!isValid(a)) { returnBFlag = true; }
		} else { returnBFlag = true; }

		SumoTuple b = null;
		if (row - 1 >= 0) { b = inMemo(row - 1, column) ? getFromMemo(row - 1, column) : sumoRecursion(row - 1, column); }

		SumoTuple maxTuple = returnBFlag ? b : maximalCost(a, b);
		memoize(maxTuple, row, column);
		return maxTuple;
	}

	private SumoTuple getFromMemo (int row, int column) {
		return memo[row][column];
	}
	private boolean inMemo (int row, int column) {
		return memo[row][column] != null;
	}

	private void memoize (SumoTuple input, int row, int column) {
		this.memo[row][column] = input;
	}

	private int getTotalCost(SumoTuple tup) {
		int totalCost = 0;
		for (int i = 0; i < tup.length(); i ++) {
			totalCost += tup.getElement(i) * this.costs[i];
		}
		return totalCost;
	}

	private int getTotalWeight (SumoTuple tup) {
		int totalWeight = 0;
		for (int i = 0; i < tup.length(); i ++) {
			totalWeight = totalWeight + (tup.getElement(i) * this.weights[i]);
		}
		return totalWeight;
	}

	private boolean isValid (SumoTuple sumoTup) {
		return getTotalCost(sumoTup) <= this.maxCost;
	}

	private SumoTuple maximalCost (SumoTuple a, SumoTuple b) {
		return (getTotalWeight(a) > getTotalWeight(b)) ? a : b;
	}

	private void print (SumoTuple answer) {
		int numItems = 0;
		for (int i = 0; i < answer.length(); i++) {
			if (answer.getElement(i) == 1) {
				numItems ++;
				System.out.println("$" + costs[i] + " / " + weights[i] + " pounds\n");
			}
		}
		System.out.println(numItems + " " + pluralizer(numItems, "item") + " / $" + getTotalCost(answer) + " / " + getTotalWeight(answer) + " pounds");
	}

	private String pluralizer (int number, String word) {
		return (number != 1) ? word + "s" : word;
	}

	private class SumoTuple {
	    private int[] data;

	    public SumoTuple(int n) {
	        if (n < 0) { throw new IllegalArgumentException(); }
	        data = new int[n];
	        for (int i = 0; i < n; i++) {
	            data[i] = 0;
	        }
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

	    public SumoTuple add(SumoTuple t) {
	        if (length() != t.length()) { throw new IllegalArgumentException(); }
	        SumoTuple sum = new SumoTuple(length());
	        for (int i = 0; i < length(); i++) {
	            int add = getElement(i) + t.getElement(i);
	            if (add >= 1) { sum.setElement(i, 1); }
	        }
	        return sum;
	    }

	    @Override
	    public String toString() {
	        String result = "<";
	        for (int i = 0; i < length(); i++) {
	            result += (i > 0 ? "," : "") + data[i];
	        }
	        return result + ">";
	    }

	    private void checkIndex(int i) {
	        if (i < 0) { throw new IllegalArgumentException(); }
	        if (i >= length()) { throw new IllegalArgumentException(); }
	    }
	}
}

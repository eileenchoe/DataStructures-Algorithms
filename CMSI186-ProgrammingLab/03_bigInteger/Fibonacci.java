public class Fibonacci {

	private static BigInteger[] answers;

	public static void main (String[] args) {
		int n = Integer.parseInt(args[0]);

		answers = new BigInteger[n];
		answers[0] = BigInteger.ONE;
		answers[1] = BigInteger.ONE;
		for(int i = 2; i < n; i++) {
			answers[i] = BigInteger.ZERO;
		}
		BigInteger answer = fastFibonacci(n);
		System.out.println(answer.toString());
	}

	public static BigInteger fibonacciRecursion (int n) {
	    if (n == 0 || n == 1) {
            return answers[0];
   		}
    	return fibonacci2(n - 2).add(fibonacci2(n - 1));
	}

	public static BigInteger fastFibonacci(int n) {
		if((n == 1) || (n == 2)) {
	        return answers[0] = BigInteger.ONE;
	    }

	    if(answers[n-1].compareTo(BigInteger.ZERO) != 0) {
	        return answers[n-1];
	    }

		if(answers[n-2].compareTo(BigInteger.ZERO) == 0) {
	        answers[n-2] = fastFibonacci(n-1);
	    }

		if(answers[n-3].compareTo(BigInteger.ZERO) == 0) {
	        answers[n-3] = fastFibonacci(n-2);
	    }
	    return answers[n-2].add(answers[n-3]);
	}

	public static BigInteger fibonacci2 (int n) {
		BigInteger first = BigInteger.ZERO;
		BigInteger second = BigInteger.ONE;
		for (int i = 2; i )

	}
}




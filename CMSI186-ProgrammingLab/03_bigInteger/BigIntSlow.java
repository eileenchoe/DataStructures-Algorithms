//non scalable solution
//this example assumes you have your storage already set i.e. using an array
//or whatever to store your BigInteger

class BigIntSlow {
	
	private int number;

	//first constructor, takes a String
	public BigIntSlow ( String number) {
		this.number = Integer.parseInt(number);
	}
	//second constructor which takes an "int"
	public BigIntSlow ( int value) {
		this.number = value;
	}

	public BigIntSlow add (BigIntSlow value) {
		int answer = this.number;
		for ( int i = 0; i < value.getValue(); i ++) {
			System.out.println(answer);
			answer ++;
		}
		return new BigIntSlow(answer);
	}

	public int getValue() {
		return this.number;
	}

	public String toString() {
		return Integer.toString(this.number);
	}

	public static void main ( String [] args) {
		BigIntSlow b1 = new BigIntSlow("123");
		BigIntSlow b2 = new BigIntSlow("456");

		BigIntSlow b3 = b1.add(b2);

		System.out.println(b3);
	}
}
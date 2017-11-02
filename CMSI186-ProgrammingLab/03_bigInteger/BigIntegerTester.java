/**
    This is a test program for the thirteen auxiliary methods in public (student version of) class BigInteger.
    There are (presently) ten separate tests. Successful (unsuccessful) tests output true
    (respectively, false). Tests for main() are not included.
    TA CHECK outputs should be verified by a TA.
    Invocations that should throw an exception are not tested yet.

 */

public class BigIntegerTester {

	public static void main ( String [] args ) {
		test_constructor(); // two tests 
		test_add();  // five tests
		test_subtract(); // six tests
		test_multiply(); // six tests
		test_divide(); // five tests
		test_remainder(); // five tests
		test_toString(); // three tests
		test_compareTo(); // three tests
		test_equals(); // four tests
		test_ZERO(); // one test
		test_ONE(); // one test
		test_TEN(); // one test
		test_valueOf(); //three tests

	}

	static void test_constructor(){

		System.out.println ( "\nTWO TESTS FOR THE CONSTRUCTOR:\n" );
		
		try{
			BigInteger b = new BigInteger("1");
			System.out.println("Constructor working without exceptions: true");
		}catch (Exception e){
			System.out.println("Exception thrown when testing constructor");
		}
		
		try{
			BigInteger b = new BigInteger("a");
			System.out.println("Constructor accounting for invalid parameters: false");
		}catch (Exception e){
			System.out.println("Constructor accounting for invalid parameters: true");
		}



	}

	static void test_add() {

		System.out.println ( "\nFIVE TESTS FOR add():\n" );

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("0");
			BigInteger sum = b.add(b1);
			System.out.print("Adding Zero?: ");
			System.out.println(sum.equals(b));
		} catch(Exception e) {
			System.out.println("Exception thrown when adding zero");
		}


		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("-123456789123456789");
			BigInteger sum = b.add(b1);
			System.out.print("Adding Complement?: ");
			System.out.println(sum.equals(new BigInteger("0")));
		} catch(Exception e) {
			System.out.println("Exception thrown when adding the complement of a number to itself.");
		}

		try {
			BigInteger b = new BigInteger("2934097831972391728347612783641927841983569834695");
			BigInteger b1 = new BigInteger("9234013274012419836418634983459547689126439817263478157836453178654");
			BigInteger sum = b.add(b1);
			java.math.BigInteger a = new java.math.BigInteger("2934097831972391728347612783641927841983569834695").add(new java.math.BigInteger("9234013274012419836418634983459547689126439817263478157836453178654"));
			System.out.println("***");
			System.out.println("TA CHECK:");	
			System.out.println(sum.toString() + " is equal to " +  '\n' + a.toString() + "?" );	
		} catch (Exception e) {
			System.out.println("Exception thrown when adding very large numbers.");
		}

		try {
			BigInteger b = new BigInteger("000123456789123456789");
			BigInteger b1 = new BigInteger("000123456789123456789");
			BigInteger sum = b.add(b1);
			java.math.BigInteger a = new java.math.BigInteger("000123456789123456789").add(new java.math.BigInteger("000123456789123456789"));
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(sum.toString() + " is equal to " +  '\n' + a.toString() + "?" );

		} catch (Exception e) {
			System.out.println("Exception thrown when adding with leading zeros.");
		}

		try {
			BigInteger b = new BigInteger("888888888888888888");
			BigInteger b1 = new BigInteger("-999999999999999999");
			BigInteger sum = b.add(b1);
			java.math.BigInteger a = new java.math.BigInteger("888888888888888888").add(new java.math.BigInteger("-999999999999999999"));
			System.out.println("***");
			System.out.println("TA CHECK:");	
			System.out.println(sum.toString() + " is equal to " +  '\n' + a.toString() + "?" );

		} catch (Exception e) {
			System.out.println("Exception thrown when adding a negative addend with absolute value greater than the leading addend.");
		}
	}

	static void test_subtract() {

		System.out.println ( "\nSIX TESTS FOR subtract():\n" );

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("0");
			BigInteger difference = b.subtract(b1);
			System.out.print("Subtracting Zero?: ");
			System.out.println(difference.equals(b));
		} catch (Exception e) {
			System.out.println("Exception thrown when subtracting zero");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("123456789123456789");
			BigInteger difference = b.subtract(b1);
			System.out.print("Subtract number from itself?: ");
			System.out.println(difference.equals(new BigInteger("0")));
		} catch(Exception e) {
			System.out.println("Exception thrown when subtracting a number from itself.");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("-123456789123456789");
			BigInteger difference = b.subtract(b1);
			java.math.BigInteger a = new java.math.BigInteger("123456789123456789").subtract(new java.math.BigInteger("-123456789123456789"));
			System.out.println("***");
			System.out.println("TA CHECK:");	
			System.out.println(difference.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when subtracting the complement of a number to itself.");
		}

		try {
			BigInteger b = new BigInteger("9234013274012419836418634983459547689126439817263478157836453178654");
			BigInteger b1 = new BigInteger("2934097831972391728347612783641927841983569834695");
			BigInteger difference = b.subtract(b1);
			java.math.BigInteger a = new java.math.BigInteger("9234013274012419836418634983459547689126439817263478157836453178654").subtract(new java.math.BigInteger("2934097831972391728347612783641927841983569834695"));
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(difference.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when subtracting very big numbers");
		}

		try {
			BigInteger b = new BigInteger("000123456789123456790");
			BigInteger b1 = new BigInteger("000123456789123456789");
			BigInteger difference = b.subtract(b1);
			java.math.BigInteger a = new java.math.BigInteger("000123456790").subtract(new java.math.BigInteger("000123456789"));
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(difference.toString() + " is equal to " +  '\n' + a.toString() + "?" );
		} catch (Exception e) {
			System.out.println("Exception thrown when subtracting with leading zeros.");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("123456789123456900");
			BigInteger difference = b.subtract(b1);
			java.math.BigInteger a = new java.math.BigInteger("123456789123456789").subtract(new java.math.BigInteger("123456789123456900"));
			System.out.println("***");
			System.out.println("TA CHECK:");			
			System.out.println(difference.toString() + " is equal to " +  '\n' + a.toString() + "?" );
		} catch (Exception e) {
			System.out.println("Exception thrown when subtracting a subtrahend > minuend.");
		}		

	}

	static void test_multiply() {

		System.out.println ( "\nSIX TESTS FOR multiply():\n" );

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("0");
			BigInteger product = b.multiply(b1);
			System.out.print("Multiplying by Zero?: ");
			System.out.println(product.equals(BigInteger.ZERO));
		} catch (Exception e) {
			System.out.println("Exception thrown when multiplying by zero");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("1");
			BigInteger product = b.multiply(b1);
			System.out.print("Multiplying by One?: ");
			System.out.println(product.equals(new BigInteger("123456789123456789")));
		} catch (Exception e) {
			System.out.println("Exception thrown when multiplying by one.");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("-1");
			BigInteger product = b.multiply(b1);
			System.out.print("Multiply positive multiplicand by -1: ?");
			System.out.println(product.equals(new BigInteger("-123456789123456789")));
		} catch (Exception e) {
			System.out.println("Exception thrown when multiplying positive multiplicand by negative one.");
		}

		try {
			BigInteger b = new BigInteger("-123456789123456789");
			BigInteger b1 = new BigInteger("-1");
			BigInteger product = b.multiply(b1);
			System.out.print("Multiplying negative multiplicand by -1?:");
			System.out.println(product.equals(new BigInteger("123456789123456789")));
		} catch (Exception e) {
			System.out.println("Exception thrown when multiplying negative multiplicand by negative one.");
		}				

		try {
			BigInteger b = new BigInteger("9234013274012419836418634983459547689126439817263478157836453178654");
			BigInteger b1 = new BigInteger("2934097831972391728347612783641927841983569834695");
			BigInteger product = b.multiply(b1);
			java.math.BigInteger a = new java.math.BigInteger("9234013274012419836418634983459547689126439817263478157836453178654").multiply(new java.math.BigInteger("2934097831972391728347612783641927841983569834695"));
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(product.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when multiplying very big numbers");
			e.printStackTrace();
		}

		try {
			BigInteger b = new BigInteger("000123456789123456789");
			BigInteger b1 = new BigInteger("000123456789123456789");
			BigInteger product = b.multiply(b1);
			java.math.BigInteger a = new java.math.BigInteger("000123456789123456789").multiply(new java.math.BigInteger("000123456789123456789"));
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(product.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when multiplying with leading zeros");
		}

	}

	static void test_divide() {
		System.out.println ( "\nFIVE TESTS FOR divide():\n");

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("1");
			BigInteger quot = b.divide(b1);
			System.out.print("Dividing postive by one?: ");
			System.out.println(quot.equals(new BigInteger("123456789123456789")));
		} catch (Exception e) {
			System.out.println("Exception thrown when dividing positive by one.");
		}

		try {
			BigInteger b = new BigInteger("-123456789123456789");
			BigInteger b1 = new BigInteger("1");
			BigInteger quot = b.divide(b1);
			System.out.print("Dividing negative by one?: ");
			System.out.println(quot.equals(new BigInteger("-123456789123456789")));
		} catch (Exception e) {
			System.out.println("Exception thrown when dividing negative by one.");
		}

		try {
			BigInteger b = new BigInteger("9234013274012419836418634983459547689126439817263478157836453178654");
			BigInteger b1 = new BigInteger("2934097831972391728347612783641927841983569834695");
			BigInteger quot = b.divide(b1);
			java.math.BigInteger a = new java.math.BigInteger("9234013274012419836418634983459547689126439817263478157836453178654").divide(new java.math.BigInteger("2934097831972391728347612783641927841983569834695"));			
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(quot.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when dividing very large numbers.");
		}	

		try {
			BigInteger b = new BigInteger("000123456789123456789");
			BigInteger b1 = new BigInteger("0002");
			BigInteger quot = b.divide(b1);
			java.math.BigInteger a = new java.math.BigInteger("000123456789123456789").divide(new java.math.BigInteger("0002"));			
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(quot.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when dividing with leading zeros.");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("-2");
			BigInteger quot = b.divide(b1);
			java.math.BigInteger a = new java.math.BigInteger("123456789123456789").divide(new java.math.BigInteger("-2"));			
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(quot.toString() + " is equal to " + '\n' + a.toString() + "?");
		} catch (Exception e) {
			System.out.println("Exception thrown when dividing by negative divisor.");
		}

	}

	static void test_remainder() {
		System.out.println ( "\nFIVE TESTS FOR remainder():\n");

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("1");
			BigInteger modulo = b.remainder(b1);
			System.out.print("Testing modulo 1: ");
			System.out.println(modulo.equals(new BigInteger("0")));
		} catch (Exception e) {
			System.out.println("Exception thrown when testing modulo 1");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("-2");
			BigInteger modulo = b.remainder(b1);
			System.out.print("Testing postive mod negative: ");
			System.out.println(modulo.equals(new BigInteger("1")));
		} catch (Exception e) {
			System.out.println("Exception thrown when postive mod negative.");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("123456789123456789");
			BigInteger modulo = b.remainder(b1);
			System.out.print("Testing modulo 1: ");
			System.out.println(modulo.equals(new BigInteger("0")));
		} catch (Exception e) {
			System.out.println("Exception thrown with modulo by self");
		}

		try {
			BigInteger b = new BigInteger("9234013274012419836418634983459547689126439817263478157836453178654");
			BigInteger b1 = new BigInteger("2934097831972391728347612783641927841983569834695");
			BigInteger modulo = b.remainder(b1);
			java.math.BigInteger a = new java.math.BigInteger("9234013274012419836418634983459547689126439817263478157836453178654").remainder(new java.math.BigInteger("2934097831972391728347612783641927841983569834695"));			
			System.out.println("***");
			System.out.println("TA CHECK:");				
			System.out.println(modulo.toString() + " is equal to " + '\n' + a.toString() + "?" + '\n');
		} catch (Exception e) {
			System.out.println("Exception thrown with modulo of large numbers");
		}

		try {
			BigInteger b = new BigInteger("000123456789123456789");
			BigInteger b1 = new BigInteger("0002");
			BigInteger modulo = b.remainder(b1);
			System.out.print("Testing modulo with leading zeros: ");
			System.out.println(modulo.equals(new BigInteger("1")));				
		} catch (Exception e) {
			System.out.println("Exception thrown with modulo of number with leading zeros");
		}

	}

	static void test_toString() {
		System.out.println ( "\nTHREE TESTS FOR toString():\n");

		try {
			BigInteger b = new BigInteger("9234013274012419836418634983459547689126439817263478157836453178654");
			String c = b.toString();
			System.out.println("***");
			System.out.println("TA CHECK:");
			System.out.println(c + " is equal to " + '\n' + "9234013274012419836418634983459547689126439817263478157836453178654");
		} catch (Exception e) {
			System.out.println("Exception thrown with toString with very large numbers.");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			String c = b.toString();
			System.out.println("***");
			System.out.println("TA CHECK:");
			System.out.println(c + " is equal to " + '\n' + "123456789123456789");
		} catch (Exception e) {
			System.out.println("Exception thrown with toString");
		}

		try {
			BigInteger b = new BigInteger("000123456789123456789");
			String c = b.toString();
			System.out.println("***");
			System.out.println("TA CHECK:");
			System.out.println(c + " is equal to " + '\n' + "123456789123456789");
		} catch (Exception e) {
			System.out.println("Exception thrown when testing toString with leading zeros");
		}

	}   

	static void test_compareTo() {
		System.out.println ( "\nTHREE TESTS FOR compareTo():\n" );

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("123456789123456789");
			int c = b.compareTo(b1);
			System.out.print("Testing compareTo equality: ");
			System.out.println(c == 0);
		} catch (Exception e) {
			System.out.println("Failed at compareTo 0");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("123456789123456788");
			int c = b.compareTo(b1);
			System.out.print("Testing compareTo more than: ");
			System.out.println(c == 1);
		} catch (Exception e) {
			System.out.println("Failed at compareTo more than");
		}	

		try {
			BigInteger b = new BigInteger("123456789123456788");
			BigInteger b1 = new BigInteger("123456789123456789");
			int c = b.compareTo(b1);
			System.out.print("Testing compareTo less than: ");
			System.out.println(c == -1);
		} catch (Exception e) {
			System.out.println("Failed at compareTo less than");
		}	

	} 

	static void test_equals() {
		System.out.println ( "\nFOUR TESTS FOR equals():\n" );

		try {
			BigInteger b = new BigInteger("123456789123456789");
			String b1 = "123456789123456789";
			System.out.print("Testing equals() wrong type: ");
			System.out.println(!b.equals(b1));
		} catch (Exception e) {
			System.out.println("Failed equals().");
		}

		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("123456789123456789");
			System.out.print("Testing equals() equality: ");
			System.out.println(b.equals(b1));
		} catch (Exception e) {
			System.out.println("Failed equals().");
		}
		
		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("333");
			System.out.print("Testing equals() inequality: ");
			System.out.println( !b.equals(b1));
		} catch (Exception e) {
			System.out.println("Failed equals().");
		}
		
		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("-123456789123456789");
			System.out.print("Testing equals() negative sign: ");
			System.out.println( !b.equals(b1) );
		} catch (Exception e) {
			System.out.println("Failed equals().");
		}
		
		try {
			BigInteger b = new BigInteger("123456789123456789");
			BigInteger b1 = new BigInteger("000123456789123456789");
			System.out.print("Testing equals() leading zeros: ");
			System.out.println( b.equals(b1) );
		} catch (Exception e) {
			System.out.println("Failed equals().");
		}
		
	}

	static void test_ZERO() {
		System.out.println ( "\nONE TEST FOR ZERO:\n" );
		try { 
			System.out.println (BigInteger.ZERO.equals(new BigInteger("0")) ); 
		} catch ( Exception e ) { 
			System.out.println ( "Failed at ZERO" ); 
		}
	}

	static void test_ONE() {
		System.out.println ( "\nONE TEST FOR ONE:\n" );
		try { 
			System.out.println (BigInteger.ONE.equals(new BigInteger("1")) ); 
		} catch ( Exception e ) { 
			System.out.println ( "Failed at ONE" ); 
		}
	}

	static void test_TEN() {
		System.out.println ( "\nONE TEST FOR TEN:\n" );
		try { 
			System.out.println (BigInteger.TEN.equals(new BigInteger("10")) ); 
		} catch ( Exception e ) {
			System.out.println ( "Failed at TEN." ); 
		}
	}

	static void test_valueOf() {
		System.out.println ( "\nTHREE TESTS FOR valueOf():\n" );
		
		try {
			long l = 123456789;
			BigInteger b = BigInteger.valueOf(l);
			System.out.print("Testing a long: ");
			System.out.println(b.equals(new BigInteger("123456789")));
		} catch ( Exception e ) {
			System.out.println("Exception thrown for testing valueOf() for a positive long.");
		}
		

		try {
			long l = -123456789;
			BigInteger b = BigInteger.valueOf(l);
			System.out.print("Testing a long: ");
			System.out.println(b.equals(new BigInteger("-123456789")));
		} catch ( Exception e ) {
			System.out.println("Exception thrown for testing valueOf() for a negative long.");
		}

		try {
			long l = 0;
			BigInteger b = BigInteger.valueOf(l);
			System.out.print("Testing a long: ");
			System.out.println(b.equals(BigInteger.ZERO));
		} catch ( Exception e ) {
			System.out.println("Exception thrown for testing valueOf() for zero.");
		}

	}
}

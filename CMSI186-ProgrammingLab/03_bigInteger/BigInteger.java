import java.util.Arrays;

public class BigInteger {
	
	private int [] digits;
	private int sign = 1;
	private int [] binary;

	public int[] getDigits() {
		return this.digits;
	}

	public int getSign() {
		return this.sign;
	}

	public int[] getBinary() {
		return this.binary;
	}

	public static final BigInteger ZERO = new BigInteger("0");

	public static final BigInteger ONE = new BigInteger("1");

	public static final BigInteger TEN = new BigInteger("10");

	public static final int MAXARRAY = 400;

	public static void main(String[]args) {

		BigInteger b = new BigInteger("10");
		BigInteger b1 = new BigInteger("6");
		BigInteger answer = b.remainder(b1);
		System.out.println(answer.toString());
	}

	public BigInteger (String number) {

		if(number.charAt(0) == '-') {
				sign = -1;
				number = number.substring(1);
		}
		int sum = summer(number);

		if (sum == 0) {
			this.digits = new int[1];
			this.digits[0] = 0;
			this.sign = 0;
			this.binary = new int [1];
			this.binary[0] = 0; 
		} else {
			this.digits = reverseArray(str2DecArray(removeLeadingZeros(number)));
			this.binary = str2DecArray(dec2Binary(removeLeadingZeros(number)));
		}
		
	};

	public static int summer (String number) {
		int sum = 0;
		for( int i = 0; i < number.length(); i++) {
		    sum += Character.getNumericValue(number.charAt(i));
		}
		return sum;
	}

	public static String divideByTwo (String value) {
		value = value + "0";
		String result = "";
		for (int i = 0; i < value.length()-1; i++) {
			int current = Character.getNumericValue(value.charAt(i));
			int next = Character.getNumericValue(value.charAt(i + 1));
			int add = isIntEven(next) ? current/2 : current/2 + 5;
			result += Integer.toString(add);
		}
		return result;
	}

	public static boolean isIntEven (int value) {
		return  ((value & 1) == 0 ) ?  true : false;
	}
	
	public static String dec2Binary (String str) {
		String answer = "";
		str = reverseString(str);
		while (summer(str) > 0) {
			answer = isIntEven(Character.getNumericValue(str.charAt(0))) ? answer + "0" : answer + "1";
			str = divideByTwo(str);
		}
		return answer;
	}

	public static String binary2Dec (String str) {
		String result = "0";
		for (int i = 0; i < str.length(); i ++) {
			result = doubleDecimalString(result);
			result = (str.charAt(i) == '1') ? addone(result) : result;
		}
		return result;
	}

	public static String doubleDecimalString (String str) {
		int [] array = reverseArray(str2DecArray(str));
		return adder(array, array);
	}


	public static String addone(String str) {
		return adder(reverseArray(str2DecArray(str)), str2DecArray("1"));
	}

	public static int[] str2DecArray (String s) {
		int size = s.length();
		int index = 0;
		int[] result = new int [size];
		for(int i = 0; i < size; i ++) {
			char c = s.charAt(i);
			if (!isNumeric(Character.toString(c))) {
					throw new IllegalArgumentException("Invalid Input"); 
			};
			result[index] = Character.getNumericValue(c);
			index++;
		}
		return result;
	}

	public static boolean isNumeric(String s) {  
    	return s.matches("[-+]?\\d*\\.?\\d+");  
	}  

	public static String arrayToStringFlip (int[] array) {
		String result = "";
		for (int i = array.length -1; i >=0; i--){
			String character = Integer.toString(array[i]);
			result += character;
		}
		return removeLeadingZeros(result);
	}

	public static String arr2Str (int[] test) {
		String result = "";
		for(int i = 0; i < test.length; i ++ ){
			result += Integer.toString(test[i]);
		}
		return result;
	}

	public static String removeLeadingZeros(String source) {
    	for (int i = 0; i < source.length(); i++) {
        	char c = source.charAt(i);
        		if (c != '0' && !Character.isSpaceChar(c)) {
            	return source.substring(i);
            }
    	}
    	return source;
	}

	public static int[] removeTrailingZeros(int[] source) {
		while (source[source.length-1] == 0) {
            source = Arrays.copyOf(source, source.length-1);
        }
    	return source;
	}

	public static int[] reverseArray(int[] test) {
		for(int i = 0; i < test.length / 2; i++) {
    	int temp = test[i];
    	test[i] = test[test.length - i - 1];
    	test[test.length - i - 1] = temp;
		}
		return test;
	}

	public static String reverseString(String source) {
	    int i, length = source.length();
	    StringBuilder result = new StringBuilder(length);
	    for (i = (length - 1); i >= 0; i--){
	        result.append(source.charAt(i));
	    }
	    return result.toString();
	}

	public int[] addElement (int[] org, int added) {
    	int[] result = Arrays.copyOf(org, org.length +1);
    	result[org.length] = added;
    	return result;
	}

	public BigInteger add (BigInteger val) {
		int[] array2 = val.getDigits();
		int sign2 = val.getSign();
		int[] array1 = this.digits;

		if (sign2 == 1 && this.sign == 1 ) {
			String resultString = adder(array1, array2);
			BigInteger b = new BigInteger(resultString);
			return b;
		} else {
			if ((this.sign + sign2 == 0) && (arraycompare(this.digits, array2) == 0)) {
				return ZERO;
			} else {

			return new BigInteger(addDecision(this.digits, array2, this.sign, sign2));
			}
		}
	}

	public static String adder (int[] array1, int[] array2) {
		int length = arraycompare(array1, array2) == 1 ? array1.length + 1 : array2.length + 1;
		array1 = Arrays.copyOf(array1,length);
		array2 = Arrays.copyOf(array2,length);
		int [] result = new int [length];
		int carry = 0;
		int i;
		for (i = 0; i < length; i++) {
			int rem = (array2[i] + array1[i] + carry) % 10;
			carry = ((array2[i] + array1[i]) + carry > 9) ? 1 : 0;
			result[i] = rem;
		} 
		if (carry == 1) {
			result[i] = 1;
		}
		return arrayToStringFlip(result);
	}

	public static int[] adder2 (int[] array1, int[] array2) {
		int length = arraycompare(array1, array2) == 1 ? array1.length + 1 : array2.length + 1;
		array1 = Arrays.copyOf(array1,length);
		array2 = Arrays.copyOf(array2,length);
		int [] result = new int [length];
		int carry = 0;
		int i;
		for (i = 0; i < length; i++) {
			int rem = (array2[i] + array1[i] + carry) % 10;
			carry = ((array2[i] + array1[i]) + carry > 9) ? 1 : 0;
			result[i] = rem;
		} 
		if (carry == 1) {
			result[i] = 1;
		}
		return (result);
	}	

	public static int [] binaryAdder (int[] array1, int[] array2) {
		int length = arraycompare(array1, array2) == 1 ? array1.length + 1 : array2.length + 1;
		array1 = Arrays.copyOf(array1,length);
		array2 = Arrays.copyOf(array2,length);
		int [] result = new int [length];
		int carry = 0;
		int i;
		for (i = 0; i < length; i++) {
			int rem = (array2[i] + array1[i] + carry) % 2;
			carry = ((array2[i] + array1[i]) + carry > 1) ? 1 : 0; 
			result[i] = rem;
		} 
		if (carry == 1) {
			result[i] = 1;
		}
		return result;
	}

	public BigInteger subtract ( BigInteger val ) {
		int [] digits2 = val.getDigits();
		int sign2 = val.getSign();
		return new BigInteger(subtractDecision(this.digits, digits2, this.sign, sign2));

	}

	public static String addDecision (int[] digits1, int[] digits2, int sign1, int sign2) {
		int signSum = sign1 + sign2;
		int signComp = signcompare(sign1,sign2);
		if (Math.abs(signSum) == 2) {
			return (signSum < 0 ? "-" + adder(digits1, digits2) : adder(digits1, digits2) );
		} else {
			return (signComp > 0 ? subtractDecision(digits1, digits2, 1, 1) : subtractDecision(digits2,digits1,1,1));
		}
	}

	public static String subtractDecision (int[] digits1, int[] digits2, int sign1, int sign2) {
		int signSum = sign1 + sign2;
		int arrComp = revarraycompare(digits1, digits2);
		int signComp = signcompare(sign1,sign2);
		
		if (signSum == 2) {
			String answer = ((arrComp == 1) ? subtracter(digits1, digits2) : "-" + subtracter(digits2, digits1));
			return answer;
		} else if (signSum == -2) {
			String answer = ((arrComp == 1) ? "-" + subtracter(digits1, digits2) : subtracter(digits2, digits1));
			return answer;
		} else {
			if (signComp == 1) {
				return adder(digits1, digits2);
			} else {
				return ("-" + adder(digits1, digits2));
			}

		}
	}

	public static String subtracter (int [] bigger, int[] smaller) {
		int length = bigger.length + 1;
		int[] big = Arrays.copyOf(bigger, length);
		int[] small = Arrays.copyOf(smaller, length);
		int [] result = new int [length];
		int borrow = 0;
		for (int i = 0; i < length; i++) {
			if (big[i] >= small[i]) {
				result[i] = (big[i] - borrow) - small[i];
				borrow = 0;
			} else {
				result[i] = (big[i] - borrow + 10) - small[i];
				borrow = 1;
			}
		}
		return arrayToStringFlip(result);
	}

	public static int[] subtracter2 (int [] bigger, int[] smaller) {
		int length = bigger.length + 1;
		int[] big = Arrays.copyOf(bigger, length);
		int[] small = Arrays.copyOf(smaller, length);
		int [] result = new int [length];
		int borrow = 0;
		for (int i = 0; i < length; i++) {
			if (big[i] >= small[i]) {
				result[i] = (big[i] - borrow) - small[i];
				borrow = 0;
			} else {
				result[i] = (big[i] - borrow + 10) - small[i];
				borrow = 1;
			}
		}
		return (result);
	}

	public BigInteger multiply ( BigInteger val ) {
		if (this.sign == 0 || val.getSign() ==0) {
			return ZERO;
		} else {
			int signSum = this.sign + val.getSign(); 
			String answer = (Math.abs(signSum) == 2) ? binary2Dec(russianPeasantMult(this.binary, val.getBinary())) : "-"  + binary2Dec(russianPeasantMult(this.binary, val.getBinary()));
			return new BigInteger(answer); 
		}
	}

	public static String russianPeasantMult (int[] one, int[] two) {
		int [] result = new int [MAXARRAY];
		while (one.length >= 1) {
			if(!isBinaryEven(one)) {
				result = binaryAdder(result, two); 
			}
			one = remove0(one);
			two = add0(two);
		}
		return arrayToStringFlip(result);
	}

	public static int[] remove0 (int[] one) {
		return Arrays.copyOfRange(one, 1, one.length);
	}

	public static int[] add0 (int[] two) {
		return reverseArray(Arrays.copyOf(reverseArray(two), two.length + 1));
	}

	public static boolean isBinaryEven (int[] array) {
		return array[0] == 0 ? true : false;
	}

	public BigInteger divide ( BigInteger val ) {
		int[] dividend = this.digits;
		int [] divisor = val.getDigits();
		String result = "";
		if (val.getSign() == 0 || this.sign == 0) {
   			throw new ArithmeticException("Argument 'divisor' is 0");
		} else if (val.getSign() == 1 && this.sign == 1) {
			result = arrayToStringFlip(dividerArrays(dividend, divisor));
		} else {
			result = "-" + arrayToStringFlip(dividerArrays(dividend, divisor));
		}
		BigInteger b = new BigInteger(result);
		return b;
	}

	public static int[] dividerArrays ( int[] a, int[] b) {
		if ( revarraycompare(b,a) ==  1 ) {
			int[] answer = new int[1];
			answer[0] = 0;
			return answer;
		} else {
			int[] result = new int[1];
			result[0] = 1;
			int [] intermediate = b;
			int [] test = intermediate;
			test = multiply10(test);
			while ( revarraycompare((test), a) == -1 || revarraycompare((test),a) == 0) {
				result = multiply10(result);
				intermediate = multiply10(intermediate);
				test = multiply10(test);
			}
			int [] x = subtracter2(a, intermediate);
			return adder2(result, dividerArrays(x, b));
		}
	}

	public static int[] multiply10(int[] input) {
		input = reverseArray(input);
		input = Arrays.copyOf(input, input.length + 1);
		return reverseArray(input);
	}

	public BigInteger remainder ( BigInteger val ) {
		
		int[] dividend = this.digits;
		int [] divisor = val.getDigits();
		int [] answer =  new int [MAXARRAY];
		answer = dividerArrays(dividend, divisor);
		String resString = arrayToStringFlip(answer);
		String binary = dec2Binary(resString);
		int [] binaryarray = str2DecArray(binary); 
		String a = binary2Dec(russianPeasantMult(binaryarray, val.getBinary()));
		int [] a1 = reverseArray(str2DecArray(a));
		String finalstr = subtracter(dividend, a1);
		BigInteger b = new BigInteger(finalstr);
		return b;
	} 

	public String toString () {
		String stringdecimal = (this.sign > -1) ? arrayToStringFlip(this.digits) : "-" + arrayToStringFlip(this.digits);
		return stringdecimal;
	}

	public int compareTo ( BigInteger val ) {
		int sign2 = val.getSign();
		int [] digits2 = val.getDigits();
		if (this.sign == sign2) {
			return arraycompare(this.digits, digits2);
		} else {
			return (this.sign>sign2 ? 1 : -1);
		}
	}

	public static int arraycompare (int[] arr1, int[] arr2) {
		if (arr1.length < arr2.length) {return -1;}
        if (arr1.length > arr2.length) {return 1;}
        for (int i=0; i<arr1.length; i++) {
            int b1 = arr1[i];
            int b2 = arr2[i];
            if (b1 < b2)
                return -1;
            if (b1 > b2)
                return 1;
        }
        return 0;
	}

	public static int revarraycompare (int[] arr1, int[] arr2) {
		int length = arraycompare(arr1, arr2) == 1 ? arr1.length + 1 : arr2.length + 1;
		arr1 = Arrays.copyOf(arr1, length);
		arr2 = Arrays.copyOf(arr2, length);
        for (int i = arr1.length - 1; i >= 0; i--) {
            int b1 = arr1[i];
            int b2 = arr2[i];
            if (b1 < b2)
                return -1;
            if (b1 > b2)
                return 1;
        }
        return 0;
	}

	public static int signcompare(int one, int two) {
		if(one > two) {return 1;}
		else if (two > one) {return -1;}
		else {return 0;}
	}

	public boolean equals(Object x) {
		if (x != null) {
			if (x instanceof BigInteger) {
				BigInteger y = (BigInteger) x;
				int[] digits2 = y.getDigits();
				int sign2 = y.getSign();
				if (this.sign != sign2) {
					return false;
				} else {
					return arraycompare(this.digits, digits2) == 0 ? true : false;
				}
			}
		}
		return false;
	}
	
	public static BigInteger valueOf ( long val ) {
		String longtoString = Long.toString(val);
		BigInteger b = new BigInteger(longtoString);
		return b;
	}
}
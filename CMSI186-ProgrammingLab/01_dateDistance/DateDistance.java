public class DateDistance2 {
	public static boolean isCommonYear (long year) {
		boolean isCommonYr;
		isCommonYr = ! (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
		return isCommonYr;
	}

	public static long monthLength (long month, long year) {
		int numDays = 0;
		if (month == 1 || month == 3 || month ==7 || month ==8 || month ==10 || month ==12) {
			numDays = 31;
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			numDays = 30;
		} else if  (isCommonYear(year)) {
			numDays = 28;
		} else {
			numDays = 29;
		}
		return numDays;
	}

	public static boolean isRealDate ( long month, long day, long year ) {
		boolean isRealDt = true;
		if (month < 0 || day < 0 || year < 0) {
			isRealDt = false;
		} else if (day > monthLength(month,year) || month > 12) {
			isRealDt = false;
		}
		return isRealDt;
	};

	public static long distance ( long month0, long day0, long year0, long month1, long day1, long year1 ) {
		long distance = 0;
		long k = day0;
		long j = month0;
		long i = year0;
		while (i <= year1) {
			while (j <= 12) {
				while (k < monthLength(j,i) && (k != day1 && j != month1 && i != year1)) {
					distance ++;
					k ++;
				}
				k = 1;
				j ++;
			}
			j=1;
			i ++;
		}
		return distance;
	}
	

	public static void main (String[]args) {
		int month0 = Integer.parseInt(args[0]);
		int day0 = Integer.parseInt(args[1]);
		int year0 = Integer.parseInt(args[2]);
		int month1 = Integer.parseInt(args[3]);
		int day1 = Integer.parseInt(args[4]);
		int year1 = Integer.parseInt(args[5]);

		System.out.println(distance(month0, day0, year0, month1, day1, year1));
	}
}
Program all of these from first principles, e.g., you may not use any of the material in java.util.Calendar, or anything else of that sort! Note that 1-4 are methods that ultimately use a return statement to return a value, e.g., in #1 and #3, your methods will return a boolean. More about this on Thursday, but here are some illustrative programs you should take a look at:
SumOfDigits.java expressed as just a main() method
SumOfDigits.java as a main() method and a value-returning method
SumOfDigits.java with an example of a recursive method
Make method public static boolean isCommonYear ( long year ), which returns true iff the specified year happens to be a common year, i.e., not a Leap Year. Strong suggestion: Make sure that you know the definition of Leap Year!
Make method public static long monthLength ( long month, long year ), which returns the number of days in the specified month.
Make method public static boolean isRealDate ( long month, long day, long year ), which returns true iff the arguments constitute a real, i.e., valid date. Some examples of invalid dates are:
0 15 2010
1 -5 2012
2 29 1700
1 32 2001
4 31 2014
5 1 -2000
16 16 2009
Make method public static long distance ( long month0, long day0, long year0, long month1, long day1, long year1 ), which returns the distance, in days, from one date to another. For example, distance ( 3, 1, 2000, 3, 5, 1999) should return 362, and distance ( 3, 2, 1999, 5, 11, 2001) should return 801. Note that the order in which the dates are specified is irrelevant- although they must, of course, be real dates.
Make a complete program, DateDistance, which outputs the distance, in days, between two given dates. The dates will be specified via args[0] through args[5]; as before, their order should not be relevant to your program. Your program will, of course, use your programmed solutions to #'s 1-4, above.

Make Select.java, which takes an arbitrary dataset of integers, with repetitions permitted, plus a positive int, k, and outputs the kth smallest number in the dataset. The dataset will be a textfile on standard input, with one integer on each line of the file. The value of k will be specified by args[0]. The program should output BAD DATA if it encounters any problem with the dataset or with k (e.g., k is out of range). Here's an example and some additional ground rules:

java Select 507 < OneMillionIntegers.txt would output the 507th smallest number from the textfile named OneMillionIntegers.txt.

A technique for dealing with standard I/O in Java is explained here.

Your program should use a java.util.ArrayList to grab, and count, the data.

Your program must employ a randomized partition, which may consume no more than a constant amount of additional storage (i.e., over and above the ArrayList that holds the data).

You must construct, perform, and document a set of experiments that confirms the expected-case linear-time performance of your program.

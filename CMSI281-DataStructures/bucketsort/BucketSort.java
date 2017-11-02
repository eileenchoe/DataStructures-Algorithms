import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author Eileen Choe
 * Bucket Sort implementation
 * Takes arbitrary file of doubles (i.e., both the amount of data and their range are arbitrary) from standard input,
 * then outputs them in ascending order.
 *
 */


public class BucketSort {

    /**
    *
    * Runner for the BucketSort program.
    * Uses standard IO conventions to read from standard input, then instantiates a BucketSort object to sort the data.
    *
    */

    public static void main (String args[]) throws Exception {
        BufferedReader stdIn = new BufferedReader ( new InputStreamReader ( System.in ) );
        ArrayList<Double> list = new ArrayList<Double>();
        Double min = Double.POSITIVE_INFINITY;
        Double max = Double.NEGATIVE_INFINITY;
        try {
          String s = stdIn.readLine();
          while (s != null) {
            Double input = Double.parseDouble(s);
            min = (input < min) ? input : min;
            max = (input > max) ? input : max;
            list.add(input);
            s = stdIn.readLine();
          }
        } catch (Exception e) {
          System.out.println("BAD DATA");
          return;
        }

        BucketSort listToSort = new BucketSort(list, min, max);
        // long before = java.lang.System.currentTimeMillis();
        listToSort.sort();
        // long after = java.lang.System.currentTimeMillis();
        // System.out.println((after - before) + " ms");

    }

    ArrayList<Double> list;
    ArrayList<ArrayList<Double>> bucketArray;
    Double min;
    Double max;
    int size;

    /**
    * Single constructor for BucketSort
    * @param list Numbers to sort
    * @param min Minimum valued entry in the list of numbers
    * @param max Maximum valued entry in the list of numbers
    *
    */

    public BucketSort(ArrayList<Double> list, Double min, Double max) {
        this.list = list;
        this.min = min;
        this.max = max;
        this.size = list.size();
        this.bucketArray = new ArrayList<ArrayList<Double>>(size);
        for (int i = 0; i < this.size; i++) {
            this.bucketArray.add(new ArrayList<Double>());
        }
    }

    /**
    * Runs the bucketsort algorithm, then prints the result
    */

    public void sort () {
        putInBucketsAndSort();
        print();
        // Double [] answer = printB();
        // checkVaildity(answer);
    }

    /**
    *
    * Enters the data in the list into buckets, then calls insert sort on the buckets
    *
    */

    private void putInBucketsAndSort() {
        double bucketSize = (this.max - this.min) / this.size;
        boolean negativeMin = (this.min < 0) ? true : false;

        for (double entry : this.list) {
            double numerator = negativeMin ? entry + Math.abs(this.min) : entry - Math.abs(this.min);
            int index = (int) Math.floor(numerator / bucketSize);
            index = (index < 0) ? 0 : index;
            index = (index > size - 1) ? size - 1 : index;
            this.bucketArray.get(index).add(entry);
        }

        for (int i = 0; i < this.size; i++) {
            this.bucketArray.set(i, insertSort(this.bucketArray.get(i)));
        }

    }

    /**
    * Insert sort implementation
    * @param list ArrayList to be sorted
    * @return sorted ArrayList
    */

    private static ArrayList<Double> insertSort (ArrayList<Double> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0; j--) {
                if(list.get(j) < list.get(j - 1)){
                    Double temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);
                }
            }
        }
        return list;
    }

    /**
    *
    * Iterates through the bucketArray to print the final, ordered list
    *
    */

    private void print () {
        for (int i = 0; i < this.size; i++) {
            ArrayList<Double> bucket = this.bucketArray.get(i);
            if (bucket.size() > 0) {
                for (int j = 0; j < bucket.size(); j++) {
                    System.out.println(bucket.get(j));
                }
            }
        }
    }

    // Methods used to check the vaildity of my bucket sort against the sorting algorithm in java Collections.

    // public Double[] printB () {
    //     int index = 0;
    //     Double [] answer = new Double[this.size];
    //     for (int i = 0; i < this.size; i++) {
    //         ArrayList<Double> bucket = this.bucketArray.get(i);
    //         if (bucket.size() > 0) {
    //             for (int j = 0; j < bucket.size(); j++) {
    //                 answer[index] = bucket.get(j);
    //                 index++;
    //                 // System.out.println(bucket.get(j));
    //             }
    //         }
    //     }
    //     return answer;
    // }
    //
    // public void checkVaildity(Double[] answer) {
    //     Collections.sort(this.list);
    //     for (int i = 0; i < this.size; i++) {
    //         if(!(this.list.get(i).equals(answer[i]))) {
    //             System.out.println("ERROR!!!");
    //         }
    //     }
    // }

}

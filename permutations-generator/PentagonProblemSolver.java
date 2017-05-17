import java.util.*;

public class PentagonProblemSolver {

  public static void main (String args[]) {

    int[] test = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    solveIteratively(test);
  }

  public static void solveIteratively(int[] arr) {
    int count = 0;
    count += isCorrectAnswerStartCorner(arr);
    for (int i = 0; i < fact(arr.length) - 1; i++) {
      int k = findK(arr);
      int l = findL(arr, k);
      arr = swapAndReverse(arr, k , l);
      count += isCorrectAnswerStartCorner(arr);
    }
    System.out.println("Total number of solutions is : " + count);
  }

  public static int findK (int[] arr) {
    int k = 0;
    for (int i = 0; i < arr.length - 1; i ++) {
      if (arr[i] < arr[i + 1]) {
        if (k < i) {
          k = i;
        }
      }
    }
    return k;
  }

  public static int findL (int[] arr, int k) {
    int l = 0;
    for (int i = k + 1; i < arr.length; i++) {
      if (arr[k] < arr[i]) {
        if (i > l) {
          l = i;
        }
      }
    }
    return l;
  }

  public static int[] swapAndReverse (int[] arr, int k, int l) {
    arr = swap(k, l, arr);
    arr = reverse(k, arr);
    return arr;
  }

  public static int [] swap (int a, int b, int[] arr) {
    int save = arr[a];
    arr[a] = arr[b];
    arr[b] = save;
    return arr;
  }

  public static int[] reverse (int k, int[] arr) {
    int [] arrayClone = Arrays.copyOf(arr, arr.length);
    int reverseIndex = arr.length - 1;
    for (int i = k + 1; i < arr.length; i++) {
      arrayClone[i] = arr[reverseIndex];
      reverseIndex--;
    }
    return arrayClone;
  }

  public static int fact (int number) {
    int answer = 1;
    for (int i = 1; i <= number; i++) {
      answer = answer * i;
    }
    return answer;
  }

  public static int isCorrectAnswerStartCorner (int[] test) {
    int sumA = test[0] + test[1] + test[2];
    int sumB = test[2] + test[3] + test[4];
    int sumC = test[4] + test[5] + test[6];
    int sumD = test[6] + test[7] + test[8];
    int sumE = test[8] + test[9] + test[0];
    if ((sumA == sumB) && (sumB == sumC) && (sumC == sumD) && (sumD == sumE)) {
      System.out.println(Arrays.toString(test) + "       sum: " + sumA);
      return 1;
    };
    return 0;
  }

}

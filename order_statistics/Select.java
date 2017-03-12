import java.util.ArrayList;
import java.io.*;
import java.util.Random;

public class Select {
  public static void main (String[] args) throws Exception {
    int k = Integer.parseInt(args[0]);
    BufferedReader stdIn = new BufferedReader ( new InputStreamReader ( System.in ) );
    ArrayList<Integer> list = new ArrayList<Integer>();
    try {
      String s = stdIn.readLine();
      while (s != null) {
        Integer line = Integer.parseInt(s);
        list.add(line);
        s = stdIn.readLine();
      }
      if (!inputCheck(list, k)) {
        throw new IOException();
      }
    } catch (Exception e) {
      System.out.println("BAD DATA");
      return;
    }

    // Random rand = new Random();
    // int randomNum = rand.nextInt((k - 1) + 1) + 1;

    /*writeFile1("test1.txt", 10000);
    writeFile1("test2.txt", 100000);
    writeFile1("test3.txt", 500000);
    writeFile1("test4.txt", 1500000);
    writeFile1("test5.txt", 7000000);
    writeFile1("test6.txt", 25000000);*/

    // long time1 = java.lang.System.currentTimeMillis();
    Select test = new Select(list);
    System.out.println(test.master(k));
    // long time2 = java.lang.System.currentTimeMillis();
    // System.out.println("time: "+ (time2 - time1));

  }

  private ArrayList<Integer> list;

  Select (ArrayList<Integer> input) {
    list = input;
  }

  /*public static void writeFile1(String filename, int inputs) throws IOException {
    File fout = new File(filename);
    FileOutputStream fos = new FileOutputStream(fout);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    for (int i = 0; i < inputs; i++) {
      int random = randInt(0, inputs);
      bw.write(Integer.toString(random));
      bw.newLine();
    }
    bw.close();
  }

  public static int randInt(int min, int max) {
    max = max / 100;
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }*/

  public static boolean inputCheck(ArrayList<Integer> list, int k) {
    if (k <= 0 || k > list.size()) { return false; } return true;
  }

  private int partition (boolean part1, int leftBound, int rightBound, int pivot) {
    if (part1) {
      pivot = leftBound + (int) Math.floor(Math.random() * (rightBound - leftBound + 1));
    }
    boolean rightToLeft = true;
    while (!(pivot == leftBound && pivot == rightBound)) {
      if (rightToLeft && pivot != rightBound) {
        int comparison = (list.get(pivot)).compareTo(list.get(rightBound));
        boolean comparisonA = part1 ? comparison < 0 : comparison <= 0;
        if (comparisonA) {
            rightBound --;
        } else {
            list = swap(list, pivot, rightBound);
            rightToLeft = false;
            pivot = rightBound;
          }
      } else {
        int comparison = (list.get(pivot)).compareTo(list.get(leftBound));
        boolean comparisonA = part1 ? comparison >= 0 : comparison > 0;
        if (comparisonA) {
          leftBound ++;
        } else {
          list = swap(list, pivot, leftBound);
          rightToLeft = true;
          pivot = leftBound;
        }
      }
    }
    return pivot;
  }

  public int master (int a) {
    return selectHelp(0, list.size() - 1, a - 1);
  }

  private Integer selectHelp (int leftBound, int rightBound, int k) {
    int x = partition(true, leftBound, rightBound, -1);
    int y = partition(false, leftBound, rightBound, x);
    if (k <= x && k >= y) { return list.get(x); }
    if (k < y) {
      return selectHelp(leftBound, y - 1, k);
    } else {
      return selectHelp(x + 1, rightBound, k);
    }
  }

  private static ArrayList<Integer> swap (ArrayList<Integer> list, int pivot, int pointer) {
    Integer save = list.get(pivot);
    list.set(pivot, list.get(pointer));
    list.set(pointer, save);
    return list;
  }
}

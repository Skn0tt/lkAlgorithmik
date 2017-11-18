import java.util.Scanner;

public class Test {
  private static String GREETING =
    "Recursive Methods by Skn0tt.\n";
  private static String CHOOSE =
    "Which Sequence do you want?\n" +
      "0: Fibonacci\n" +
      "1: Hanoi\n" +
      "2: Hofstadter\n";
  private static String CACHED =
    "Do you want to use the cached variant? [true/false]";
  private static String INVALID_INPUT =
    "Invalid Input. Try again!";
  private static String INPUT =
    "n = ";
  private static String TIME =
    "Laufzeit: ";
  private static String OUTPUT =
    "Result: ";
  private static String CHOOSE_HANOI =
    "Which Implementation do you want?\n" +
      "0: Recursive\n" +
      "1: Explicit\n" +
      "2: Iterative\n";

  static Scanner s = new Scanner(System.in);

  public static void main(String... args) {
    greet();
    int selection = choose();
    int n = input();

    long start = -1;
    int result = -1;
    int implementation = -1;
    boolean cached = false;

    switch (selection) {
      case 0:
        cached = cached();
        start = System.currentTimeMillis();
        result = cached ? Fibonacci.fibRecCache(n) : Fibonacci.fibRec(n);
        break;

      case 1:
        implementation = choose_hanoi();
        start = System.currentTimeMillis();
        switch (implementation) {
          case 0:
            result = Hanoi.hanoiRec(n);
            break;
          case 1:
            result = Hanoi.hanoiExp(n);
            break;
          case 2:
            result = Hanoi.hanoiIt(n);
        }
        break;

      case 2:
        cached = cached();
        start = System.currentTimeMillis();
        result = cached ? Hofstadter.hofstadterQRecCache(n) : Hofstadter.hofstadterQRec(n);
        break;
    }
    long stop = System.currentTimeMillis();
    output(result, stop - start);
  }

  static void greet() {
    System.out.println(GREETING);
  }

  static int choose() {
    System.out.println(CHOOSE);
    while(true) {
      int n = s.nextInt();
      if (n >= 0 && n <= 2) return n;
      System.out.println(INVALID_INPUT);
    }
  }

  static int input() {
    System.out.println(INPUT);
    while(true) {
      int n = s.nextInt();
      if (n >= 0) return n;
      System.out.println(INVALID_INPUT);
    }
  }

  static boolean cached() {
    System.out.println(CACHED);
    return s.nextBoolean();
  }

  static int choose_hanoi() {
    System.out.println(CHOOSE_HANOI);
    while(true) {
      int n = s.nextInt();
      if (n >= 0 && n <= 2) return n;
      System.out.println(INVALID_INPUT);
    }
  }

  static void output(int result, long time) {
    System.out.println(OUTPUT + result);
    System.out.println(TIME + time + "ms");
  }
}

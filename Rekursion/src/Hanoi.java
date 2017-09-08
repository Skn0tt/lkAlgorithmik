import java.util.Scanner;

/**
 * Created by simon.knott on 08.09.2017.
 */
public class Hanoi {
    private static String GREETING =
            "Hanoi Calculator by Skn0tt.";
    private static String INPUT =
            "n = ";
    private static String OUTPUT =
            "Züge: ";
    private static String TIME =
            "Laufzeit: ";

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        System.out.println(GREETING);
        System.out.print(INPUT);
        int n = s.nextInt();
        if (n < 0) throw new IllegalArgumentException("Illegal Argument N!");
        System.out.print(OUTPUT);

        long start = System.currentTimeMillis();
        System.out.println(hanoiRec(n));
        long stop = System.currentTimeMillis();

        System.out.print(TIME);
        System.out.println(stop - start);
    }

    /**
     * Recursive Hanoi Implementation
     * Idea: Z(n) = 2 * Z(n-1) + 1
     * @param n number of disks
     * @return number of turns needed
     */
    static int hanoiRec(int n) {
        return n == 1 ? 1 : 2 * hanoiRec(n-1) + 1;
    }

    /**
     * Explicit Hanoi Implementation
     * Idea: Z(n) = 2^n - 1
     * @param n number of disks
     * @return number of turns needed
     */
    static int hanoiExp(int n) {
        return (int) Math.pow(2, n) - 1;
    }

    /**
     * Iterative Hanoi Implementation
     * Idea: Z(n) = 2 * Z(n-1) + 1
     * @param n number of disks
     * @return number of turns needed
     */
    static int hanoiIt(int n) {
        int last = 0;
        for (int i = 0; i < n - 1; i++) last = 2 * last + 1;

        return 2 * last + 1;
    }
}

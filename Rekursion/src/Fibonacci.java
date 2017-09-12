import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by simon.knott on 08.09.2017.
 */
public class Fibonacci {
    private static String GREETING =
            "Fibonacci Calculator by Skn0tt.\n" +
            "Do you want to use the cached variant? [true/false]";
    private static String INPUT =
            "n = ";
    private static String OUTPUT =
            "fib: ";
    private static String TIME =
            "Laufzeit: ";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println(GREETING);
        boolean cached = s.nextBoolean();
        System.out.print(INPUT);
        int n = s.nextInt();
        if (n < 0) throw new IllegalArgumentException("Illegal Argument N!");
        System.out.print(OUTPUT);

        long start = System.currentTimeMillis();
        System.out.println(cached ? fibRecCache(n) : fibRec(n));
        long stop = System.currentTimeMillis();

        System.out.print(TIME);
        System.out.println(stop - start);
    }

    /**
     * Recursive Fibonacci Implementation
     * @param n Index in Fibonacci row
     * @return fib(n)
     */
    static int fibRec(int n) {
        return n <= 2 ? 1 : fibRec(n - 1) + fibRec(n - 2);
    }

    /**
     * Recursive Fibonacci Implementation with Cache
     * @param n Index in Fibonacci row
     * @return fib(n)
     */
    static int fibRecCache(int n) {
        if (cache.size() <= n) cache.add(fibRecCache(n - 1) + fibRecCache(n - 2));
        return cache.get(n);
    }
    static List<Integer> cache = new ArrayList<>(Arrays.asList(0, 1));
}

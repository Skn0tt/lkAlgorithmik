import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by simon.knott on 08.09.2017.
 */
public class Fibonacci {
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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by simon.knott on 08.09.2017.
 */
public class Fibonacci {
    static List<Integer> cache = new ArrayList<>();
    public static void main(String[] args){
        // cache Setup
        cache.add(0);
        cache.add(1);

        Scanner s = new Scanner(System.in);
        //int n = s.nextInt();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 50; i++) {
            System.out.println(fibRecCache(i));
        }

        long stop = System.currentTimeMillis();
        System.out.println(stop - start);
    }

    static int fibRec(int n) {
        return n <= 2 ? 1 : fibRec(n - 1) + fibRec(n - 2);
    }

    static int fibRecCache(int n){
        if (cache.size() <= n) cache.add(fibRecCache(n - 1) + fibRecCache(n - 2));
        return cache.get(n);
    }
}

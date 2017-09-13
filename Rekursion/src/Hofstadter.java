import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by simon.knott on 13.09.2017.
 */
public class Hofstadter {
    public static void main(String... args){
        System.out.println(hofstadterRecCache(50));
    }

    static int hofstadterRec(int n) {
        if (n <= 2) return 1;
        return hofstadterRec(n - hofstadterRec(n - 1)) + hofstadterRec(n - hofstadterRec(n - 2));
    }

    static int hofstadterRecCache(int n) {
        if (cache.size() <= n) cache.add(hofstadterRecCache(n - hofstadterRecCache(n - 1)) + hofstadterRecCache(n - hofstadterRecCache(n - 2)));
        return cache.get(n);
    }
    static List<Integer> cache = new ArrayList<>(Arrays.asList(0, 1, 1));
}

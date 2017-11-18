import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by simon.knott on 13.09.2017.
 */
public class Hofstadter {
  /**
   * Recursive Implementation of the Hofstadter Q-Sequence
   * @param n index
   * @return Q(n)
   */
  static int hofstadterQRec(int n) {
    if (n <= 2) return 1;
    return hofstadterQRec(n - hofstadterQRec(n - 1)) + hofstadterQRec(n - hofstadterQRec(n - 2));
  }

  /**
   * Recursive Implementation of the Hofstadter Q-Sequence with caching
   * @param n index
   * @return Q(n)
   */
  static int hofstadterQRecCache(int n) {
    if (cache.size() <= n)
      cache.add(hofstadterQRecCache(n - hofstadterQRecCache(n - 1)) + hofstadterQRecCache(n - hofstadterQRecCache(n - 2)));
    return cache.get(n);
  }
  static List<Integer> cache = new ArrayList<>(Arrays.asList(0, 1, 1));
}

import java.util.Scanner;

/**
 * Created by simon.knott on 08.09.2017.
 */
public class Hanoi {
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

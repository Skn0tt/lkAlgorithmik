import java.util.Scanner;

/**
 * Created by simon.knott on 08.09.2017.
 */
public class Hanoi {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        System.out.println(hanoiRec(n));
    }

    /**
     * Recursive Hanoi Implementation
     * @param n number of disks
     * @return number of turns needed
     */
    static int hanoiRec(int n) {
        return n == 1 ? 1 : 2 * hanoiRec(n-1) + 1;
    }

    /**
     * Explicit Hanoi Implementation
     * @param n number of disks
     * @return number of turns needed
     */
    static int hanoiExp(int n) {
        return (int) Math.pow(2, n) - 1;
    }

    /**
     * Iterative Hanoi Implementation
     * @param n number of disks
     * @return number of turns needed
     */
    /*
    static int hanoiIt(int n) {
        int vorletzter;
        int letzter;
        for (int i = 0; i < n; i++) {

        }

        return count + 1;
    }
    */
}

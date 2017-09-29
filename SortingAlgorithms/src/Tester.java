/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 24.08.2015
  * @author B.Reichelt
  */
import java.util.Scanner;


public class Tester {
  public static void main(String[] args) {
    RekursivSortieren s = new RekursivSortieren();
    Scanner eingabe = new Scanner(System.in);
    System.out.print("n=");
    int n = eingabe.nextInt();

    //Erzeuge ein Array mit vierzig Werten zwischen 0 und n
    s.erzeugeNeuesArray(n, 0, n);

    //Ausgeben der Werte
    System.out.println("Werte unsortiert:");
    System.out.println(s.arrayAusgeben());

    //Sortieren
    s.sort();

    //Ausgeben der Werte
    System.out.println(s.arrayAusgeben());
  } // end of main
} // end of class Tester

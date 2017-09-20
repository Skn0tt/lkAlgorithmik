/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 18.09.2017
  * @author B. Reichelt
  */
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class BinSuche {
  // Util
  Random random = new Random();

  // Anfang Attribute
  private int[] array;

  // Ende Attribute
  public BinSuche() {
    erzeugeNeuesArray(50, 1, 100);
  }

  // Anfang Methoden
  public int binSucheRek(int gesZahl, int links, int rechts) {
    //TODO
    
    return 0;
  }

  public void erzeugeNeuesArray(int anzahl, int minWert, int maxWert) {
    array = new int[anzahl];

    for (int i = 0; i < anzahl; i++) array[i] = random(minWert, maxWert);

    //Array Sortieren
    java.util.Arrays.sort(array);
  }

  private int random(int min, int max) {
    return random.nextInt(max - min + 1) + min;
  }

  public String arrayAusgeben() {
    IntStream stream = Arrays.stream(array):


    return "";
  }

  // Ende Methoden
} // end of Suche

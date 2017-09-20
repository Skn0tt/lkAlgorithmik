/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 18.09.2017
  * @author B. Reichelt
  */
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.IntStream;


public class BinSuche {
  // Util
  private Random random = new Random();

  // Anfang Attribute
  private int[] array;

  // Ende Attribute
  public BinSuche() {
    erzeugeNeuesArray(50, 1, 100);
  }

  // Anfang Methoden
  private int mittel(int left, int right) {
    return (left + right) / 2;
  }

  private int binSucheRek(int gesZahl, int links, int rechts) {
    if (links == rechts) {
      if (array[links] == gesZahl) return links;
      else return -1;
    }

    int mitte = mittel(links, rechts);
    int mitteWert = array[mitte];

    if (mitteWert == gesZahl) return mitte;
    if (mitteWert > gesZahl) return binSucheRek(gesZahl, links, mitte);

    return binSucheRek(gesZahl, mitte + 1, rechts);
  }

  public int suche(int gesucht) {
    return binSucheRek(gesucht, 0, array.length);
  }

  private int random(int min, int max) {
    return random.nextInt(max - min + 1) + min;
  }

  public void erzeugeNeuesArray(int anzahl, int minWert, int maxWert) {
    array = new int[anzahl];

    for (int i = 0; i < anzahl; i++) array[i] = random(minWert, maxWert);

    //Array Sortieren
    java.util.Arrays.sort(array);
  }

  public String arrayAusgeben() {
    StringJoiner sj = new StringJoiner(" ");
    IntStream.of(array).forEach(x -> sj.add(String.valueOf(x)));

    return sj.toString();
  }

  // Ende Methoden
} // end of Suche

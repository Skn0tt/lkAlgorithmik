/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 24.08.2015
  * @author
  */
import java.util.Random;


public class RekursivSortieren {
  // Anfang Attribute
  private int[] array;

  // Ende Attribute
  public RekursivSortieren() {
    erzeugeNeuesArray(50, 1, 100);
  }
  // Anfang Methoden
  public int getRechts() {
    return this.array.length - 1;     
  }

  public void sort() {
    this.mergeSort(0, getRechts());
  }
  
  //mergeSort rekursiv
  public void mergeSort(int left, int right) {
    if (left == right) return;
    int center = (left + right) / 2;
    mergeSort(left, center);
    mergeSort(center + 1, right);
    merge(left, right);
  }
  
  //mergen
  private void merge(int low, int high) {
    int center = (low + high) / 2;
    int[] tempA = new int[center + 1 - low];
    int temp = 0;
    for (int i = low; i <= center; i++) tempA[temp++] = array[i];
    int i = low;
    int j = center + 1;
    temp = 0;
    while (i < j && j <= high) {
      if (tempA[temp] <= array[j]) array[i++] = tempA[temp++];
      else array[i++] = array[j++];
    }
    while (temp < tempA.length) array[i++] = tempA[temp++];
  }
  
  /**
   * Erzeugt ein neues Array der Länge Anzahl mit Zufallszahlen zwischen minWert und maxWert
   * @param anzahl Anzahl der Werte im Array
   * @param minWert
  */  
  public void erzeugeNeuesArray(int anzahl, int minWert, int maxWert) {
    array = new int[anzahl];
    
    Random zufallsgenerator = new Random();
    
    //Array mit Zufallszahlen belegen
    for (int j = 0; j < array.length; j++) {
      array[j] = zufallsgenerator.nextInt(maxWert - minWert + 1) + minWert;
    }
    
  }
  
  public String arrayAusgeben() {
    String res = array[0] + "\t";
    
    for (int i = 1; i < array.length; i++) {
      if ((i % 10) == 0) {
        res = res + "\n";
      } // end of if
      
      res = res + array[i] + "\t";
    } // end of for
    
    return res;
  }
  
  // Ende Methoden
} // end of Suche

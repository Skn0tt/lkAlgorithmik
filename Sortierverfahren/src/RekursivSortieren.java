/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 24.08.2015
  * @author
  */
import java.util.*;


class RekursivSortieren {
  // Anfang Attribute
  private int[] array;

  // Ende Attribute
  public RekursivSortieren() {
    erzeugeNeuesArray(50, 1, 100);
  }
  // Anfang Methoden
  private int getRechts() {
    return this.array.length - 1;     
  }

  private void swap(int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  /*
  # selectionSort
   */
  public void selectionSort() {
    this.selectionSort(getRechts());

    assert isSorted();
  }

  private void selectionSort(int high) {
    if (high < 1) return;

    int maxIndex = 0;

    for (int i = 0; i <= high; i++) {
      if (array[i] > array[maxIndex]) maxIndex = i;
    }

    swap(high, maxIndex);

    selectionSort(high - 1);
  }

  /*
  # bubbleSort
   */
  public void bubbleSort() {
    this.bubbleSort(getRechts());

    assert isSorted();
  }

  private void bubbleSort(int high) {
    if (high < 1) return;

    for (int i = 1; i <= high; i++) {
      if (array[i - 1] > array[i]) swap(i - 1, i);
    }

    bubbleSort(high - 1);
  }

  /*
  # quickSort
   */
  public void quickSort() {
    this.quickSort(0, getRechts());

    assert isSorted();
  }

  private void quickSort(int low, int high) {
    if (high - low < 1) return;

    int pivot = array[(high + low) / 2];

    int l = low;
    int r = high;

    while (l <= r) {
      while (array[l] < pivot) l++;
      while (array[r] > pivot) r--;

      if (l <= r) {
        swap(l, r);
        l++;
        r--;
      }
    }

    quickSort(low, r);
    quickSort(l, high);
  }

  /*
  # mergeSort
   */
  public void mergeSort() {
    this.mergeSort(0, getRechts());

    assert isSorted();
  }
  
  //mergeSort rekursiv
  private void mergeSort(int left, int right) {
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

  private boolean isSorted() {
    int last = array[0];
    for (int i = 1; i < array.length; i++) {
      if (Integer.compare(last, array[i]) > 0) return false;
      last = array[i];
    }

    return true;
  }
  
  /**
   * Erzeugt ein neues Array der L�nge Anzahl mit Zufallszahlen zwischen minWert und maxWert
   * @param anzahl Anzahl der Werte im Array
   * @param minWert minimaler Wert
   * @param maxWert maximaler Wert
  */  
  public void erzeugeNeuesArray(int anzahl, int minWert, int maxWert) {
    array = new int[anzahl];
    
    Random zufallsgenerator = new Random();
    
    //Array mit Zufallszahlen belegen
    for (int j = 0; j < array.length; j++) array[j] = zufallsgenerator.nextInt(maxWert - minWert + 1) + minWert;
  }
  
  public String arrayAusgeben() {
    StringBuilder res = new StringBuilder(array[0] + "\t");
    
    for (int i = 1; i < array.length; i++) {
      if ((i % 10) == 0) {
        res.append("\n");
      } // end of if
      
      res.append(array[i]).append("\t");
    } // end of for
    
    return res.toString();
  }
  
  // Ende Methoden
} // end of Suche

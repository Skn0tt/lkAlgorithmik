# Rekursives Sortieren

Suchen mit Binary Search ist zwar schnell ($O(log(n))$), benötigt aber einen bereits sortierten Wertebereich.
Sortieren dauer immer lange.

## Merge Sort
Man teile die Menge solange, bis man nur noch einen Wert hat.
Dann fügt man die Werte wieder zusammen, und achtet dabei auf die richtige Reihenfolge.

### Pseudocode
```
funktion mergeSort()
  wenn nur noch ein element
    dann tue nichts
  sonst
    teile die menge in der mitte
    mergeSort() für den linken wertebereich
    mergeSort() für den rechten wertebereich
    füge die beiden wertebereiche wieder zusammen
```

### Implementierung
```java
void mergeSort(int left, int right) {
  if (left == right) return;

  int center = (left + right) / 2;
  mergeSort(left, center);
  mergeSort(center + 1, right);
  merge(left, right);
}
```

`merge()` ist hier eine Blackbox: Uns ist es egal, wie die werte zusammengeführt werden, hauptsache sie werden richtig zusammengefügt.

## merge()
Die `merge()`-Methode wird oben angewendet, sie funktioniert wie folgt:

Meine beiden Bereiche werden als Stapel betrachtet, sie sind jeweils schon sortiert.
Nun iteriere ich durch meinen Wertebereich und trage den jeweils kleineren Wert der Stapel ein.
Danach ist der Bereich sortiert.

### Implementierung
```java
void merge(int low, int high) {
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
```

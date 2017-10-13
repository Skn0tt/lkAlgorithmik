# quickSort

Quicksort verfolgt, so wie Mergesort, den Ansatz *"Divide and Conquer"*.

Dabei geht Quicksort wie folgt vor:
Man sucht sich ein Element aus der Menge heraus, das sogenannte Pivot-Element.  
Nun sorgt man dafür, dass sich das Pivot-Element an seiner endgültigen Position befindet.  
Dann macht man das gleiche für die Bereiche links und rechts von diesem Element.
![Quicksort Beispiel](https://upload.wikimedia.org/wikipedia/commons/f/fe/Quicksort.gif)
<!--more-->

## Funktionsweise
### Pseudocode
```pseudo
function quickSort(low, high)
  falls es nur einen Wert gibt: Tue nichts.
  Suche dir einen Wert aus, nenne diesen Pivot

  solange (
    es links von Pivot einen Wert gibt, der größer als Pivot ist UND
    es rechts von Pivot einen Wert gibt, der kleiner als Pivot is
  )
    Tausche diese Werte

  quickSort(low, r - 1);
  quickSort(r + 1, right);
```

*quickSort* funktioniert wie folgt:
Man suche sich ein Pivot-Element aus (Z. 3).
Nun sorgt man dafür, dass links von diesem Element nur kleinere/gleiche, rechts von diesem Element nur größere/gleiche Werte stehen (Z. 5-9).
Hat man diesen Schritt getan, so weiß man: Das Pivot-Element steht an seiner endgültigen Position.
Nun sortiert man alle Elemente links bzw. rechts vom Pivot-Element (Z. 12, 13).
Als Rekursionsbasis dient der Fall eines einzelnen Elements, dann wird nichts getan (Z. 2).

### Implementierung
```java
void quickSort(int low, int high) {
  if (high - low < 1) return;

  int pivot = array[(high + low) / 2];
  int l = low;
  int r = high;

  while (r > l) {
    while (array[l] < pivot) l++;
    while (array[r] > pivot) r--;
    
    swap(l, r);
  }

  quickSort(low, r - 1);
  quickSort(r + 1, high);
}
```

## Laufzeit
Die Wahl des Pivot-Elements spielt eine entscheidende Rolle für die Effizienz des Algorithmus.

Im schlimmsten Fall wählt man immer das kleinste/größte Element, dann wird der zu sortierende Bereich immer nur um eins kleiner.
Es ergibt sich die Laufzeit $O(n^2)$: Ich mache für jedes meiner $n$ Elemente einen Durchlauf, ein Durchlauf bedeutet einmal durch $n$ Werte iterieren.

Im besten anzunehmenden Fall treffe ich jedes Mal den Mittelwert, mein Bereich wird also immer zu gleichen Teilen aufgeteilt.
Es ergibt sich die Laufzeit $O(n \cdot log(n))$: Ich habe $log_2(n)$ Rekursionsebenen und damit $log_2(n)$ durchläufe, ein Durchlauf bedeutet einmal durch $n$ Werte iterieren.

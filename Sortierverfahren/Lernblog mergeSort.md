# mergeSort

Mit der [binären Suche](https://simonknott.de/articles/BinarySearch) kann man zwar sehr schnell suchen, man benötigt aber einen bereits sortierten Wertebereich.
Sortieren dauert immer etwas länger, im folgenden erkläre ich den Ansatz *mergeSort*.

## mergeSort
Das Konzept ist relativ simpel:
Man teile die Menge solange, bis man nur noch einen Wert hat.
Dann fügt man die Werte wieder zusammen, und achtet dabei auf die richtige Reihenfolge.

![Beispiel mergeSort](https://upload.wikimedia.org/wikipedia/commons/c/cc/Merge-sort-example-300px.gif)

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
Zeile 2 überprüft, ob nur noch ein Element betrachtet wird.
Ist dem so, `return`ed sie, es wird abgebrochen.
Dies ist unsere Rekursionsbasis.

In allen anderen Fällen wird die Mitte bestimmt (Z. 4).
Dann wird `mergeSort()` erneut für den linken (Z. 5) sowie rechten (Z. 6) Bereich aufgerufen, zum Schluss werden die Bereiche mit `merge()` wieder vereint (Z. 7).

`merge()` wird hier als Blackbox betrachtet: Uns ist es egal, wie die Werte zusammengeführt werden, solange dabei kein Fehler geschieht.

##### Ist das nicht ein unvollständiger Algorithmus?

Es ist nicht festgelegt, wie genau die Bereiche wieder vereint werden.
Dementsprechend kann man nur anhand dieses Algorithmus nichts sortieren, richtig.

Gegenfrage: Hat mal jemand festgelegt, wie man $a$ und $b$ addiert?
Hat mal jemand festgelegt, wie man bestimmt ob $a < b$ ist?
Nein, das hat niemand.

Ich kann $a$ und $b$ addieren indem ich mir $a$ Äpfel und $b$ Bananen hinlege und die Summe zählen, ich kann das ganze aber auch mit Strichen machen, erst beide Zahlen auf Werte zwischen $0$ und $1$ skalieren und dann die Summe wieder in den Ursprungsbereich zurückskalieren - es gibt keinen festgelegten Algorithmus dafür.

Trotzdem kann ich $a$ und $b$ addieren, trotzdem weiß ich wie ich $a^2 + b^2 = c^2$ benutze.
Ganz ohne einen festgelegten Algorithmus für $a + b$ oder $a^n$.

Das Stichwort lautet *Abstraktion* und ist der Hauptbestandteil der Informatik - *"Managing Complexity"* ist das Leitmotiv.

## merge
Nachdem das Prinzip der *Abstraktion* nun klar geworden sein sollte, beschreibe ich noch kurz den Algorithmus für `merge()`, den ich in meiner Implementatin angewendet habe.

Meine beiden Bereiche werden als Stapel betrachtet, die jeweils schon sortiert sind.
Ich iteriere nun durch meinen Wertebereich und trage den jeweils kleineren Wert der beiden Stapel ein, danach wird der Wert vom Stapel entfernt.

![Struktogramm merge](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/Struktogramm%20merge.jpg?alt=media&token=c4ff2018-b232-4820-be61-5b89d883eb0b)

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
Zuerst wird die Mitte bestimmt (Z. 2).
`tempA` stellt den ersten Stapel dar.
Dieser wird mit den Werten von links bis zur Mitte befüllt (Z. 4 - 6).
Der zweite Stapel ist immer noch in `array` enthalten.
Der Wert `temp` ist ein Zeiger auf den obersten Wert des ersten Stapels (Z. 9), der Wert `j`zeigt auf den obersten Wert des zweiten Stapels (Z. 8).
Mithilfe der Durchlaufvariable `i` (Z. 7) wird durch `array` iteriert (Z. 10 - 13).
Innerhalb der Schleife wird nun geprüft, welcher der beiden Werte der Stapel der kleinere der beiden ist, und in `array[i]` gespeichert.
Nun wird `i` und der jeweilige Zeiger auf die Stapel `temp`/`j` inkrementiert (Z. 11, Z. 12).
Zum Schluss werden alle übrigen Werte des ersten Stapels `tempA` noch übertragen, damit keine Werte verloren gehen.


## Laufzeit
Um die Worst-Case-Laufzeit von *mergeSort* zu bestimmen, schauen wir uns zunächst einige Fälle an:

* mit einem Schritt kann ich zwei Werte ($2^1$) sortieren
* mit zwei Schritten kann ich vier Werte ($2^2$) sortieren
* mit drei Schritten kann ich acht Werte ($2^3$) sortieren
* usw...

Die maximale Anzahl der Werte in Abhängigkeit der Schritte lässt sich also wie folgt darstellen:
$$
MaximaleWerte = 2^n \\
\Rightarrow O(log(n))
$$

Nun muss man das Array aber nicht nur teilen, sondern auch noch zusammenfügen.
Dafür iteriert man über das gesamte Array, man hat also $n$ Schritte zusätzlich.
Dies geschieht nach jedem Teilen, es ergibt sich also insgesamt die Laufzeit
$$
O(n \cdot log(n))
$$

### Übrigens...
Für die Laufzeit benutzt man die sogenannte *Big O-Notation*: $O(g(n))$ bedeutet, dass die Laufzeit des Algorithmus proportional zu $g(n)$ verläuft.
Es gibt allerdings hier noch besondere Schreibweisen:
Ein großes *Theta*($\Theta$) bezeichnet die Laufzeit im *Average-Case* bzw. den *Tight Bound*: $\Theta(g(n))$
Ein großes *Omega* ($\Omega$) bezeichnet die Laufzeit im *Best-Case* bzw. den *Lower Bound*: $\Omega(g(n))$
Ein großes *O* bezeichnet die Laufzeit im *Worst-Case* bzw. den *Upper Bound*: $O(g(n))$

Eine Merkhilfe dafür:
Zeichnet man $\Omega$, so ist die Linie unten - der *Lower Bound*.
Zeichnet man $\Theta$, so ist die Linie in der Mitte - der *Tight Bound*.
Zeichnet man $O$, so hört man meist oben wieder auf - der *Upper Bound*.


Quellen: [Mehrdad Afshari @ stackoverflow.com](https://stackoverflow.com/a/471206), [Andrei Krotkov @ stackoverflow.com](https://stackoverflow.com/a/471470)

# quickSort
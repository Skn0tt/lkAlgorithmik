# Game of Life

Das *Game of Life* wurde in den 1970er Jahren von John Horton Conway entworfen.
Es basiert auf einem simplen Modell des Zellzyklus und soll diesen veranschaulichen.
Das Modell wurde für einen zweidimensionalen Automaten entwickelt und lief auch auf diesem.

### Prinzip

Es gibt ein Spielfeld der Maße $ N \cdot N $.
Jeder Punkt dieses Spielfelds stellt eine Zelle dar, die entweder lebendig oder tot sein kann.

Fünf *Übergangsregeln* bestimmen, ob eine Zelle im nächsten Zug lebt:
1. **Geburt:** Eine *tote* Zelle lebt im nächsten Zug, wenn sie exakt *3* lebende Nachbarn hat: `!alive && lebendeNachbarn == 3`
2. **Tod:** Eine *tote* Zelle bleibt tot, wenn sie *nicht* exakt *3* lebende Nachbarn hat: `!alive && lebendeNachbarn != 3`
3. **Überleben:** Eine *lebende* Zelle bleibt lebend, wenn sie *2* oder *3* Nachbarn hat: `alive && (lebendeNachbarn == 2 || lebendeNachbarn == 3)`
4. **Vereinsamung:** Eine *lebende* Zelle stirbt, wenn sie *weniger* als *2* lebende Nachbarn hat: `alive && lebendeNachbarn < 2`
5. **Überbevölkerung:** Eine *lebende* Zelle stirbt, wenn sie *mehr* als *3* lebende Nachbarn hat: `alive && lebendeNachbar > 3`

Als Beispiel (die jeweils mittlere Zelle wird betrachtet):
![Example 1](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL1.svg?alt=media&token=7110946c-b22d-42df-80b3-edcc0258efe2)
![Example 2](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL2.svg?alt=media&token=6672e365-386e-464b-aea9-5c5ac3f47c1f)
![Example 3](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL3.svg?alt=media&token=866ee74c-6bd4-4a0e-aecf-725c85aa0711)
![Example 4](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL4.svg?alt=media&token=863bb180-f00c-4e8e-904f-c5c1e1ac4b72)
![Example 5](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL5.svg?alt=media&token=a74d6f19-d7d9-48f7-ac09-4addd3913e70)

### Implementierung

Im gegebenen Raster sollten zwei Methoden vervollständigt werden.
##### anzahlLebender

```java
int anzahlLebender(int i, int j) {
  return zustand[i - 1][j - 1] +
          zustand[i][j - 1] +
          zustand[i + 1][j - 1] +
          zustand[i - 1][j] +
          zustand[i + 1][j] +
          zustand[i - 1][j + 1] +
          zustand[i][j + 1] +
          zustand[i + 1][j + 1];
}
```
Diese Methode ermittelt für den übergebenen Punkt $x[i|j]$ die Anzahl der Nachbarn.
Dabei wird einfach die Summe der Zustände der Zellen um $x$ zurückgegeben.
Die Variable `zustand` ist dabei Global und enthält als 2D-Array die Zustände aller Zellen. Diese werden als `int` gespeichert, *0* steht für tot, *1* steht für lebend.
Eine `ArrayIndexOutOfBoundsException` kann nicht auftreten, da diese Methode nie für einen Punkt am Rand des 2D-Arrays aufgerufen wird.

##### uebergang
```java
public Gitter uebergang(Graphics g) {
  int[][] zustandNeu = new int[N][N];

  for (int row = 1; row < N - 1; row++) {
    for (int col = 1; col < N - 1; col++) {
      final int anzahlLebende = anzahlLebender(row, col);
      final boolean alive = zustand(row, col) == 1;

      if (!alive) { // Falls schon tot
        if (anzahlLebende == 3) zustandNeu[row][col] = 1; // Falls Tot && 3 Nachbarn: Geburt
      } else if (anzahlLebende == 2 || anzahlLebende == 3) zustandNeu[row][col] = 1;  // Falls lebendig und 2-3 Nachbarn, Bleib am Leben
      else zustandNeu[row][col] = 0;  // Sonst: Tod
    }
  }

  return new Gitter(g, zustandNeu);
}
```
Sowohl `Graphics g` als auch der Rückgabetyp `Gitter` können ignoriert werden, diese sind für den Algorithmus nicht von Belang.
Diese Methode hat die Aufgabe, aus dem alten Zustand mithilfe der *Übergangsregeln* den neuen Zustand zu ermitteln.
Dafür wird ein neues, leeres Array `zustandNeu` initialisiert und nun mit den entsprechenden Werten gefüllt.
Die beiden äußeren Schleifen iterieren über `zustandNeu`.
Dann wird für jede Zelle die Anzahl der lebenden Nachbarn und der aktuelle Zustand ermittelt und in `anzahlLebende` sowie `alive` zwischengespeichert.

Falls die Zelle schon tot ist und die Anzahl der lebenden Nachbarn exakt 3 beträgt, wird die betrachtete Zelle im nächsten Zustand nach *Übergangsregel 1* leben.
Sie wird auf 1 gesetzt.

Falls die Zelle schon lebt und 2 oder 3 Nachbarn hat, darf sie nach *Übergangsregel 3* überleben.
Sie wird auf 1 gesetzt.

In jedem anderen Fall ist *Übergangsregel 2, 4* oder *5* eingetroffen, nach der die Zelle stirbt oder tot bleibt.
Sie wird auf 0 gesetzt.

### Besondere Muster

Durch diese Regeln gibt es einige besondere Muster, die sich bilden lassen.
![Patterns](http://www.math.cornell.edu/~lipa/mec/4life2.png)

In diesem Video sind einige sehr interessante, Komplexe Muster und Abfolgen dargestellt:

<iframe width="560" height="315" src="https://www.youtube.com/embed/C2vgICfQawE" frameborder="0" allowfullscreen></iframe>

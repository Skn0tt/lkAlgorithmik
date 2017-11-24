# Backtracking

## Backtracking Allgemein

Beim Backtracking probiert man jede mögliche Lösung aus, überprüft allerdings mit jedem Schritt die Abbruchbedingungen des Problems.
Dabei ist der Algorithmus immer sehr ähnlich aufgebaut:

### Pseudocode
```pseudo
backtrack()
  WENN Problem gelöst
    GIB Lösung ZURÜCK
  
  Finde Element im Ursprungszustand
  DURCHLAUFE jeden Zustand
    WENN Zustand zulässig
      Setze Zustand um

      WENN backtrack() Lösung findet
        GIB Lösung ZURÜCK
      
      Mache Zustand Rückgängig
  
  GIB keine Lösung ZURÜCK
```

Für jedes Element des Problems (Spalten im Damenproblem, Länder im Vierfarbenproblem...) werden alle möglichen Zustände ausprobiert (Z. 5f).
Ist ein Zustand zulässig (Z. 7), so wird diese Möglichkeit umgesetzt (Z. 8) und rekursiv überprüft, ob es eine Lösung für den aktuellen Zustand gibt (Z. 10).
Gibt es diesen, wird er zurückgegeben (Z. 11).
Gibt es diesen nicht, wird der vorherige Schritt rückgängig gemacht (Z. 13) und der nächste Zustand überprüft.
Sollten alle möglichen Zustände überprüft worden sein, ohne dass eine Lösung gefunden wurde, dann gibt es für den aktuellen Zustand keine Lösung und das wird dementsprechend zurückgegeben (Z. 15).

Die Rekursionsbasis stellt der Fall ein, dass das Problem gelöst ist: Dann wird einfach die Lösung zurückgegeben (Z. 2f).

### Bedingungen an das Problem
Damit ein Problem effizient per Backtracking gelöst werden kann, muss es folgende Eigenschaften besitzen:

1. Es lässt sich in $n \in \mathbb{N}$ Unterprobleme mit $k \in \mathbb{N}$ möglichen Zuständen aufteilen

## Backtracking am Beispiel des Vierfarbenproblems
Beim Vierfarbenproblem geht es darum, eine Karte mit $n$ Ländern unter Verwendung von $k$ Farben einzufärben.

Dabei müssen für jedes Feld zwei Bedingungen erfüllt sein:

> Das neu gefärbte Land grenzt an kein bereits früher gefärbtes Land mit der gleichen Farbe.

![Beispiel Bedingung 1](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/VierfarbenproblemBedingung1.png?alt=media&token=65b7cf55-aecb-4c9c-94ea-33abddd4ccfc)

> Durch die Färbung des neuen Landes wird kein angrenzendes, noch nicht gefärbtes Land unfärbbar.

![Beispiel Bedingung 2](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/VierfarbenproblemBedingung2.png?alt=media&token=3fe50585-8e9d-4de6-ac29-6d9cd872cddb)

Die Bilder zeigen jeweils Fälle, in denen die Bedingungen *nicht* erfüllt sind.

Die Zweite Bedingung ist nicht zwingend notwendig, macht die Problemlösung allerdings deutlich einfacher.

### Pseudocode
Zu Demonstrationszwecken ist der folgende Code auf $k = 4$ Felder ausgelegt.
Wir stellen erstmal den Pseudocode für unsere beiden Bedingungen auf:

```pseudo
bedingung1(i, farbe)
  FÜR alle angrenzenden
    WENN Farbe des angrenzenden gleich farbe
      GIB false ZURÜCK
  GIB true ZURÜCK
```
Um herauszufinden, ob man eine Farbe setzen kann, überprüft man jedes der angrenzenden Länder.

```pseudo
bedingung2(i)
  ERZEUGE Liste
  FÜR alle angrenzenden
    HÄNGE Farbe AN Liste AN
  
    FALLS Liste enthält Blau UND
        Liste enthält Gelb UND
        Liste enthält Grün UND
        Liste enthält Rot
      GIB false ZURÜCK
    SONST
      GIBT true ZURÜCK
```
Wir tragen die Farbe aller umliegenden Felder in eine Liste ein.
Wenn diese Liste alle $k$ Farben enthält (im Beispiel *Blau*, *Gelb*, *Rot* und *Grün*), ist die Bedingung nicht erfüllt.

Um nun das Problem zu lösen, benutzen wir folgenden Algorithmus:
```pseudo
vierFarbenProblem()
  FALLS alle Felder eingefärbt sind
    GIB Farben der Felder ZURÜCK

  finde das erste Land ohne Farbe
  DURCHLAUFE ALLE farben
    FALLS
      bedingung1(land, farbe) UND
      bedingung2(land)
    DANN
      SETZE farbe
      FALLS vierFarbenProblem() eine Lösung zurückgibt
        GIB die Lösung zurück
      SETZE farbe zurück
  GIB keine Lösung zurück
```
In Zeile 2f. ist die erste Rekursionsbasis: Sie überprüft, ob das Problem gelöst wurde.
Wenn es gelöst wurde, wird die Lösung zurückgegeben.

Greift die Rekursionsbasis nicht, gehen wir in den Schritt über:
Wir finden ein Land ohne Farbe (Z. 5) und iterieren über alle Lösungsmöglichkeiten (in unserem Fall $k$ Farben $F$).
Falls diese Farbe $F$ beiden Bedingungen genügt, wird sie "ausprobiert":
Die Farbe wird erst gesetzt (Z. 11).
Dann wird überprüft, ob es für diese Konstellation eine Lösung gibt (Z. 12).
Gibt es diese, wird sie zurückgegeben (Z. 13), sonst wird das Setzen der Farbe wieder rückgängig gemacht (Z. 14) und in der nächsten Iteration die nächste Farbe ausprobiert.
Falls über alle Farben iteriert wurde, ohne eine Lösung zu finden, gibt es für diesen Zustand keine Lösung und "keine Lösung" wird zurückgegeben (Z. 15).

### Demonstration
// Hier JS einfügen

### Präsentation
<iframe src="https://simonknott.de/slides/vierfarbenproblem" width="400" height="400" frameborder="0"></iframe>

[Das Vierfarbenproblem als Präsentation](https://simonknott.de/slides/vierfarbenproblem)

## Backtracking am Beispiel von Sudoku
Sudoku ist ein beliebtes Zahlenrätsel, bei dem es um das ausfüllen eines Zahlenfeldes nach bestimmten Regeln geht.

Eine Matrix $M$ der Dimension $9 \times 9$ (selten andere Größen) besteht aus $3 \times 3$ Submatrizen $Q$ der Dimension $3 \times 3$.
Jede Komponente sei eine natürliche Zahl $0 \geq x \geq 10$, sodass $x$ in jeder Spalte, Zeile und Submatriz einzigartig ist.

Dabei sind zu Beginn schon einige Zahlen eingetragen.

![Beispiel](http://www.texample.net/media/tikz/examples/PNG/sudoku.png)

Zur Lösung eines Sudokus kann man sehr gut Backtracking verwenden.
Dabei sind die Komponenten unsere Elemente und die Zahlen $[0; 9]$ die möglichen Zustände dieser.

### Pseudocode
```pseudo
WENN Alle Komponenten befüllt
    GIB Feld ZURÜCK
  
  Finde leere Komponente
  DURCHLAUFE 1..9
    WENN Zahl zulässig
      Setze Zahl

      WENN backtrack() Lösung findet
        GIB Lösung ZURÜCK
      
      Entferne Zahl
  
  GIB keine Lösung ZURÜCK
```
### Demonstration
https://github.com/pocketjoso/sudokuJS


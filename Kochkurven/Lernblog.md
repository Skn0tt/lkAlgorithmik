# Rekursive Kurven
Nicht nur Zahlenfolgen lassen sich rekursiv definieren, auch mit Kurven/Figuren lässt sich das Konzept der Rekursion sehr gut veranschaulichen.

Dabei gibt es im Bereich der rekursiven Kurven einige spezielle Begriffe:
- **Initiator:** das Grundgebilde, die Rekursionsbasis
- **Generator:** der Rekursionsschritt
- **Rekursionstiefe:** Anzahl der Rekursionsebenen / die Genauigkeit der Zeichnung

## Kochkurve
Die Koch-Kurve wurde 1904 von dem schwedischen Mathematiker [Helge von Koch](https://de.wikipedia.org/wiki/Helge_von_Koch) veröffentlicht.
Der Initiator ist eine simple Linie, der Generator ein stachelähnliches Gebilde aus vier Elementen tieferer Rekursionsebenen.

![Kochkurve Beispiel](https://science.kairo.at/frakt_koch.png)

### Code
```java
void koch(int t, double laenge) {
  if (t == 1) {
    zeichneStrich();
    return;
  }

  koch(t - 1, laenge / 3);
  drehe(60);
  koch(t - 1, laenge / 3);
  drehe(2 * 60 * -1);
  koch(t - 1, laenge / 3);
  drehe(60);
  koch(t - 1, laenge / 3);
}
```

## Koch'sche Schneeflocke
Die Koch'sche Schneeflocke ist ein Gebilde aus 3 Kochkurven, die jeweils um 120° gedreht wurden.
![Schneeflocke Beispiel](https://upload.wikimedia.org/wikipedia/commons/d/d9/KochFlake.svg)

## Drachenkurve
Die Drachenkurve ist ein anderes Fraktal, welches sich durch rechte Winkel und ein drachenähnliches Erscheinen auszeichnet.
![Drachenkurve](http://www.oberstufeninformatik.de/info11/turtle/DRACHEN.GIF)
```
Initiator: zeichne Strich
Generator:
  t - 1 zeichnen
  90° links drehen
  t - 1 gespiegelt zeichnen
```

## Pythagoras Baum
Der Pythagoräische Baum entstammt - entgegen dem Namen - nicht der Feder Pythagoras, sondern visualisiert seinen berühmten Satz.
Ein solcher Baum besteht aus ganz vielen kleinen 'Häusern' (im Bild blau/rot) deren Dach die Grundfläche zweier weiterer Häuser bildet.

![Beispiel Pythagoras](https://upload.wikimedia.org/wikipedia/commons/1/19/Pythagoras_tree.png)

### Pseudocode
```
1. Seite
t - 1
obere Seite
t - 1 gespiegelt
2. Seite
untere Seite
```

### Implementierung
```java
void pythagorasBaum(int t, int vz, double laenge) {
  if (t == 0) return; // Basis

  // 1. Seite
  turtle.forward(laenge);
  
  // 1. Haus
  turtle.left(angle * vz);
  pythagorasBaum(t - 1, vz, neueLaenge(laenge, angle));
  turtle.right(angle * vz);

  // Obere Seite
  turtle.right(90 * vz);
  turtle.forward(laenge);

  // 2. Haus
  turtle.left(angle * vz);
  pythagorasBaum(t - 1, vz * -1, neueLaenge(laenge, angle);
  turtle.right(angle * vz);

  // 2. Seite
  turtle.right(90 * vz);
  turtle.forward(laenge);

  // Untere Seite
  turtle.right(90 * vz);
  turtle.forward(laenge);

  // Zurück drehen
  turtle.right(90 * vz);
}
```

## Andere bekannte Fraktale

### Mandelbrotmenge
![Mandlebrotmenge](http://preshing.com/images/M.jpg)

### Sierpinski Dreieck
![Sierpinski Dreieck](https://upload.wikimedia.org/wikipedia/commons/4/45/Sierpinski_triangle.svg)

### Juliamenge
![Juliamenge](https://www.blitzforum.de/upload/file.php?id=6456)


## Ein zufälliges Fraktal
<canvas id="myCanvas" style=" position:absolute; left:0px; top:0px;">
	There's no support for canvas. 
</canvas>
<script src="http://rawgit.com/pesout/fractal/master/fractal.js"></script>
### Quellcode
Mein Quellcode ist einsehbar unter [github.com/Skn0tt/lkAlgorithmik/tree/master/Kochkurven](https://github.com/Skn0tt/lkAlgorithmik/tree/master/Kochkurven).

# Rekursive Kurven
Rekursive Kurven sind rekursiv definierte Kurven.
Wichtige Begriffe:

- Initiator: Grundgebilde, Rekursionsbasis
- Generator: Rekursionsschritt
- Rekursionstiefe: Genauigkeit der Zeichnung

## Kochkurve
![Kochkurve Beispiel](https://science.kairo.at/frakt_koch.png)
```
Initiator: 1 Strich zeichnen
Generator: 
  t - 1 zeichnen
  60° links drehen
  t - 1 zeichnen
  120° rechts drehen
  t - 1 zeichnen
  60° links drehen
  t - 1 zeichnen
```
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

## Kochsche Schneeflocke
![Schneeflocke Beispiel](https://upload.wikimedia.org/wikipedia/commons/d/d9/KochFlake.svg)
Besteht aus drei Kochkurven

```
3 mal
  kochkurve zeichnen
  120° rechts drehen
```

## Drachenkurve
![Drachenkurve](http://www.oberstufeninformatik.de/info11/turtle/DRACHEN.GIF)
```
Initiator:
  zeichneStrich
Generator:
  t - 1 zeichnen
  90° links drehen
  t - 1 gespiegelt zeichnen
```

```java
void drachen(int t, int vz, int laenge) {
  if (t == 0) {
    zeichneStrich()
    return;
  }

  drachen(t - 1, 1, laenge);
  drehen(90 * vz);
  drachen(t - 1, -1, laenge);
}
```
Rekursionsbasis: t == 0 -> zeichneStrich()
vz sorgt für richtige Spiegelung

## Pythagoras Baum
![Beispiel Pythagoras](https://upload.wikimedia.org/wikipedia/commons/1/19/Pythagoras_tree.png)
```
1. Seite
t - 1
obere Seite
t - 1 gespiegelt
2. Seite
untere Seite
```
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

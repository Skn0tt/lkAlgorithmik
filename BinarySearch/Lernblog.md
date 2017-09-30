# Binäre Suche

In einer sortierten Menge ist eines der besten Suchverfahren die Binäre Suche: Die Laufzeit beträgt $O(log(n))$.

Es funktioniert wie folgt:
Ich teile betrachte den Mittelwert der Menge.
Ist er der gesuchte Wert, bin ich fertig und habe meinen Wert gefunden.
Ist er das nicht, vergleiche ich diesen Wert mit dem gesuchten.
Ist er kleiner, so führe ich diesen Algorithmus für den Bereich links der Mitte aus.
Ist er größer, so fähre ich diesen Algorithmus für den Bereich rechts der Mitte aus.
Enthält mein Suchbereich nur noch einen Wert, so prüfe ich, ob dieser der gesuchte Wert ist.
Ist er das nicht, dann ist der gesuchte Wert auch nicht in der Menge enthalten.

![Animation Binary Search](https://media.giphy.com/media/WbdMEyP252goM/giphy.gif)

### Pseudocode
```
suche(gesWert, links, rechts)
  wenn Array nur 1 Wert hat
    wenn Wert gefunden dann gib Index zurück
    sonst gib -1 zurück
  sonst
    wenn mittlerer Wert richtig ist
      dann gib index davon zurück
    sonst
      wenn gesuchter wert kleiner ist
        suche(gesWert, links, mitte)
      sonst
        suche(gesWert, mitte, rechts)
```

### Implementierung
```java
int binSuche(int gesucht, int links, int rechts) {
  if (links == rechts) {
    if (array[links] == gesucht) return links;
    else return -1;
  }

  int mitte = (links + rechts) / 2;
  int mitteWert = array[mitte];

  if (mitteWert == gesucht) return mitte;
  if (mitteWert > gesucht) return binSuche(gesucht, links, mitte);

  return binSuche(gesucht, mitte + 1, rechts);
}
```

### Laufzeit
Zu Beginn erwähnte ich, dass die Binäre Suche die Laufzeit $O(log(n))$ hat.
Wieso ist das so?

Mit jedem Suchschritt verdoppele ich die Anzahl der Werte, die ich ausschließen kann.
Damit beträgt Anzahl der Werte, die nach $n$ Schritten durchsucht wurden $2^n$ - nach $n$ umgestellt ergibt sich die Laufzeit $O(log(n))$.


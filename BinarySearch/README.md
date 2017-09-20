# Binary Search

## Suchstrategie
`left` sei zu Beginn $$0$$, `right` die Anzahl der Werte
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

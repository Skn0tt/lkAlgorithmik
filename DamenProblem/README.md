# nDamen-Problem
Lösung per Backtracking

## Pseudo
```
function backtrack
    betrachte alle möglichkeiten in der zeile
      
    für jede möglichkeit
        setzte dame auf Möglichkeit
        führe backtrack für nächste Zeile aus
        wenn Lösung gefunden
            dann gib Lösung zurück
        nimm dame wieder weg#
        
    gib keine lösung zurück
    
```
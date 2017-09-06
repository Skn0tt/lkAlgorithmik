# Game of Life

* Umgesetzt v. John Horton Conway
* Simulation des Lebensverlaufs v. Zellen
* Entwickelt f. 2D-Automaten

### Prinzip:

Ein `N * N` großes Spielfeld sei gegeben.
Jeder Punkt des Feldes stellt eine Zelle dar, somit hat jede Zelle 8 Nachbarn:
```
ooo
oxo
ooo
```

Fünf Regeln bestimmen, ob eine Zelle im nächsten Zug lebt:
1. **Geburt:** Eine *tote* Zelle lebt im nächsten Zug, wenn sie exakt *3* lebende Nachbarn hat: `!alive && lebendeNachbarn == 3`
2. **Tod:** Eine *tote* Zelle bleibt tot, wenn sie *nicht* exakt *3* lebende Nachbarn hat: `!alive && lebendeNachbarn != 3`
3. **Überleben:** Eine *lebende* Zelle bleibt lebend, wenn sie *2* oder *3* Nachbarn hat: `alive && (lebendeNachbarn == 2 || lebendeNachbarn == 3)`
4. **Vereinsamung:** Eine *lebende* Zelle stirbt, wenn sie *weniger* als *2* lebende Nachbarn hat: `alive && lebendeNachbarn < 2`
5. **Überbevölkerung:** Eine *lebende* Zelle stirbt, wenn sie *mehr* als *3* lebende Nachbarn hat: `alive && lebendeNachbar > 3`

Daraus folgt:

```java
if (!alive) {
  if (lebendeNachbarn == 3) lebendig();
} else if (lebendeNachbarn == 2 || lebendeNachbarn == 3) lebendig()
else tot();
```

Beispiele, jeweils mittlere Zelle wird betrachtet:

![Example 1](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL1.svg?alt=media&token=7110946c-b22d-42df-80b3-edcc0258efe2)
![Example 2](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL2.svg?alt=media&token=6672e365-386e-464b-aea9-5c5ac3f47c1f)
![Example 3](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL3.svg?alt=media&token=866ee74c-6bd4-4a0e-aecf-725c85aa0711)
![Example 4](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL4.svg?alt=media&token=863bb180-f00c-4e8e-904f-c5c1e1ac4b72)
![Example 5](https://firebasestorage.googleapis.com/v0/b/simonknott-de.appspot.com/o/GoL5.svg?alt=media&token=a74d6f19-d7d9-48f7-ac09-4addd3913e70)
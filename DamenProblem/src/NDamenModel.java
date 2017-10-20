import java.util.ArrayList;
import java.util.List;

public class NDamenModel {
  
  private int n;
  private int[][] bedrohung;
  private Pos[] dame;
  private int anzahlDamen;
  private NDamenView view; 
  
  /**
  * Konstruktor.
  *
  * @param n     Größe des Spielfeldes. 
  * @param view  Controller, der über Ereignisse informiert werden will,
  *              die sich auf die Views auswirken könnten.
  */
  public NDamenModel(int n, NDamenView view) {
    this.n = n;                // Spielfeldgröße
    bedrohung = new int[n][n]; // 0 = Feld nicht bedroht; positiver Wert = Feld bedroht
    dame = new Pos[n];        // Positionen der bereits platzierten Damen
    anzahlDamen = 0;           // Anzahl der beeits platzierten Damen (anfangs 0)
    this.view = view;          // Um View über Änderungen zu informieren
  }
  
  public static interface Zuhoerer {
    void info(NDamenChange change);
  }
  
  /**
  * Ändert die Bedrohung eines einzelnen Feldes. Benachrichtigt den View,
  * falls (a) keine Bedrohung mehr vorliegt, oder (b) eine neue Bedrohung
  * entstanden ist. Liegt (x,y) außerhalb des Spielfeldes, geschieht nichts.
  *
  * @param x         x-Koordinate des Feldes 
  * @param y         y-Koordinate des Feldes
  * @param delta     1, zusätzliche Bedrohung, -1 falls weggefallene Bedrohung
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  */
  private void aendereBedrohung(int x, int y, int delta, Zuhoerer zuhoerer) {
    if (x<0 || y<0 || x>=n | y>=n) return;
    bedrohung[x][y]+=delta;
    if (bedrohung[x][y] == 0) zuhoerer.info(new NDamenChange(NDamenChange.KEINE_BEDROHUNG, new Pos(x,y)));
    if (bedrohung[x][y] == delta) zuhoerer.info(new NDamenChange(NDamenChange.NEUE_BEDROHUNG, new Pos(x,y)));
  }
  
  /**
  * Ändert die Bedrohungen, wenn eine Damen gesetzt oder entfernt wurde. 
  * Das Feld, auf welchem die Dame steht, wird selbst auch verändert.
  *
  * @param x         x-Pos, auf die die Dame gesetzt oder entfernt wurde. 
  * @param y         y-Pos, auf die die Dame gesetzt oder entfernt wurde.
  * @param delta     1, falls Dame gesetzt, -1 falls Dame entfernt wurde.
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  */

  private void bedrohungenDurchDame(int x, int y, int delta, Zuhoerer zuhoerer) {
    for (int i=0; i<n; i++) {
      aendereBedrohung(x, i, delta, zuhoerer);            // Spalte
      if (i!=x) aendereBedrohung(i, y, delta, zuhoerer);  // Zeile (ohne erneut eig. Feld)
      aendereBedrohung(x-i, y-i, delta, zuhoerer);        // Diag. links runter
      aendereBedrohung(x-i, y+i, delta, zuhoerer);        // Diag. links hoch
      aendereBedrohung(x+i, y-i, delta, zuhoerer);        // Diag. rechts runter
      aendereBedrohung(x+i, y+i, delta, zuhoerer);        // Diag. rechts hoch
    }
  }
  
  /**
  * Entfernt die intern an iDame-ter Stelle gespeicherte Dame und informiert
  * den View über die Änderung.
  *
  * @param iDame     Index der Dame im Array dame.
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  */
  private boolean entferneDame(int iDame, Zuhoerer zuhoerer) {
    if (iDame<0 || iDame>=anzahlDamen) {
      return false;
    }
    int x = dame[iDame].getX();
    int y = dame[iDame].getY();
    bedrohungenDurchDame(x, y, -1, zuhoerer);
    for (int i=iDame; i+1<anzahlDamen; i++) dame[i] = dame[i+1];
    anzahlDamen--;
    zuhoerer.info(new NDamenChange(NDamenChange.DAME_ENTFERNT, new Pos(x, y)));
    return true;    
  }
  
  /**
  * Entfernt die Dame an Position (x, y), falls vorhanden. Benachrichtigt den
  * View beim Entfernen.
  *
  * @param x         x-Pos, von der die Dame entfernt werden soll. 
  * @param y         y-Pos, von der die Dame entfernt werden soll. 
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  * @return          true, falls an der Position eine Dame vorhanden war, sonst false.
  */
  public boolean entferneDame(int x, int y, Zuhoerer zuhoerer) {
    for (int iDame=0; iDame<anzahlDamen; iDame++) {
      if (dame[iDame].getX()==x && dame[iDame].getY()==y) {
        return entferneDame(iDame, zuhoerer);
      }
    }
    return false;  
  }

  public boolean entferneDame(Pos p, Zuhoerer zuhoerer) {
    return entferneDame(p.getX(), p.getY(), zuhoerer);
  }
  
  /**
  * Entfernt alle Damen vom Spielfeld. Bei jedem einzelnem Entfernen wird der
  * View informiert.
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  */
  public void entferneAlleDamen(Zuhoerer zuhoerer) {
    for (int iDame=anzahlDamen-1; iDame>=0; iDame--) entferneDame(iDame, zuhoerer);
  }
  
  /**
  * Setzt eine Dame an Position (x, y), falls möglich, also gültiges Feld ohne
  * Bedrohung. Benachrichtigt den View über die Änderung.
  *
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  * @return          true, falls die Dame gesetzt werden konnte, sonst false.
  */
  public boolean setzeDame(int x, int y, Zuhoerer zuhoerer) {
    if (x<0 || x>=n || y<0 || y>=n || bedrohung[x][y] > 0) {
      return false;
    }
    dame[anzahlDamen] = new Pos(x, y);
    anzahlDamen++;
    bedrohungenDurchDame(x, y, 1, zuhoerer);
    zuhoerer.info(new NDamenChange(NDamenChange.DAME_GESETZT, new Pos(x, y)));
    return true;    
  }

  public boolean setzeDame(Pos p, Zuhoerer zuhoerer) {
    return setzeDame(p.getX(), p.getY(), zuhoerer);
  }

  public Pos[] backtrack(Zuhoerer zuhoerer) {
    return backtrack(0, zuhoerer);
  }
  
  /**
  * Sucht von der aktuellen Konstellation eine Lösung, also ein Brett, auf
  * dem n Damen stehen, die sich gegenseitig nicht bedrohen.
  *
  * @param zuhoerer  View, der über Änderungen im Model informiert werden will.
  * @return          Array mit Damen (auch die anfangs schon vorhandenen).
  */
  public Pos[] backtrack(int row, Zuhoerer zuhoerer) {
    Pos[] moeglichkeiten = getMoeglichkeiten(row);

    for (Pos p : moeglichkeiten) {
      setzeDame(p, zuhoerer);

      Pos[] loesung = backtrack(row + 1, zuhoerer);

      if (loesung != null) return loesung;

      entferneDame(p, zuhoerer);
    }

    return null;
  }

  private Pos[] getMoeglichkeiten(int row) {
    List<Pos> moeglichkeiten = new ArrayList<>();

    for (int i = 0; i < bedrohung[row].length; i++) {
      if (bedrohung[row][i] == 0) moeglichkeiten.add(new Pos(row, i));
    }

    return moeglichkeiten.toArray(new Pos[0]);
  }
}

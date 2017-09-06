import java.awt.*;

public class Gitter {
  // Gitter = Zellular-Raum

  Graphics g;
  int fensterbreite = 600;
  int feldbreite = 20;
  int N = fensterbreite / feldbreite;
  int lebend = 1, tot = 0;

  private int[][] zustand;

  public Gitter (Graphics g0){;
    g = g0;
    zustand = new int[N][N];
  }

  public Gitter (Graphics g0, int[][] z0){;
    g = g0;
    zustand = z0;
  }

  public void pentomino1() {
    // Pentomino als Anfangsfigur
    int i = N/2;
    zustand[i][i-2] = lebend;
    zustand[i][i-1] = lebend;
    zustand[i][i]   = lebend;
    zustand[i][i+1] = lebend;
    zustand[i][i+2] = lebend;
  } // Ende Pentomino1

  public void gleiter() {
    // Gleiter als Anfangsfigur
    int i = N/5;
    zustand[i][i-1]   = lebend;
    zustand[i][i]     = lebend;
    zustand[i][i+1]   = lebend;
    zustand[i-1][i+1] = lebend;
    zustand[i-2][i]   = lebend;
  } // Ende Gleiter

  public void pentomino2() {
    // R-Pentomino
    int i = N/3;
    zustand[i][i-1]   = lebend;
    zustand[i][i]     = lebend;
    zustand[i][i+1]   = lebend;
    zustand[i-1][i+1] = lebend;
    zustand[i+1][i]   = lebend;
  } // Ende Pentomino2

  public void siebenerreihe() {
    // Sieben in einer Reihe
    int i = N/2, j = N/2;
    zustand[i][j-3] = lebend;
    zustand[i][j-2] = lebend;
    zustand[i][j-1] = lebend;
    zustand[i][j]   = lebend;
    zustand[i][j+1] = lebend;
    zustand[i][j+2] = lebend;
    zustand[i][j+3] = lebend;
  } // Ende Siebenerreihe

  public void raumschiff() {
    // Leichtes Raumschiff
    int i = N/2, j = N/5;
    zustand[i-1][j-2] = lebend;
    zustand[i][j-1]   = lebend;
    zustand[i][j]     = lebend;
    zustand[i][j+1]   = lebend;
    zustand[i][j+2]   = lebend;
    zustand[i-1][j+2] = lebend;
    zustand[i-2][j+2] = lebend;
    zustand[i-3][j+1] = lebend;
  } // Ende Raumschiff

  private int anzahlLebender (int i, int j) {
    // zählt Anzahl der lebenden Nachbarn
    //ergänzen
    return zustand[i - 1][j - 1] +
            zustand[i][j - 1] +
            zustand[i + 1][j - 1] +
            zustand[i - 1][j] +
            zustand[i + 1][j] +
            zustand[i - 1][j + 1] +
            zustand[i][j + 1] +
            zustand[i + 1][j + 1];
    //bis hier hin
  } // Ende AnzahlLebender

  public Gitter uebergang (Graphics g) {
    int[][] zustandNeu = new int[N][N];
    //Hier ergänzen
    for (int row = 1; row < N - 1; row++) {
      for (int col = 1; col < N - 1; col++) {
        final int anzahlLebende = anzahlLebender(row, col);
        final boolean alive = zustand(row, col) == 1;

        if (!alive) {
          if (anzahlLebende == 3) zustandNeu[row][col] = 1;
        } else if (anzahlLebende <= 3 && anzahlLebende >= 2) zustandNeu[row][col] = 1;
        else zustandNeu[row][col] = 0;
      }
    }
    //ab hier nichts verändern
    return new Gitter(g, zustandNeu);
  } // Ende Übergang
  
  public int zustand (int zeile, int spalte){
    return zustand[zeile][spalte];
  }
  
  private void setzePunkt (int zeile, int spalte) {
    if (zustand[zeile][spalte] == lebend)
      g.setColor(Color.blue);
    else
      g.setColor(Color.white);
    g.fillRect(spalte * feldbreite+2, zeile * feldbreite+2, feldbreite-4, feldbreite-4);
  } // Ende setzePunkt
  
  private void loesche (int zeile, int spalte) {
    g.setColor(Color.white);
    g.fillRect(spalte * feldbreite+1, zeile * feldbreite+1, feldbreite-2, feldbreite-2);
  } // Ende loesche
  
  public void ausgabe() {
    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++){
        setzePunkt(i, j);
      }
    }
  } // Ende Ausgabe
  
  public void loeschen() {
    for (int i = 0; i < N; i++){
      for (int j = 0; j < N; j++){
        loesche(i, j);
      }
    }
  } // Ende loeschen
  
  public void warte (int zeitdauer) {
    try {
      Thread.sleep(zeitdauer);
    }catch(InterruptedException e) {}
  } // Ende warte
} // Ende Gitter
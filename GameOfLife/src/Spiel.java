import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Spiel extends Frame {
  // Conways "Spiel des Lebens" (Nullpersonenspiel)
  
  int schrittzahl;
  int geschwindigkeit;
  int fensterbreite = 600;
  int feldbreite = 20;
  int N = fensterbreite / feldbreite;
  int lebend = 1, tot = 0;
  
  public Spiel (int s, int v, Graphics g, int[][] zustand) {
    schrittzahl = s;
    geschwindigkeit = v;
    Gitter gitter = new Gitter(g, zustand);
    for (int i = 0; i < schrittzahl; i++) {
      gitter.ausgabe();
      g.setColor(Color.black);
      g.drawString(i+"",2,12);
      Gitter gitterNeu = gitter.uebergang(g);
      gitter = gitterNeu;
      gitter.warte(geschwindigkeit);
      gitter.loeschen();
    }
    gitter.ausgabe();
  } // Ende Konstruktor
  
  public Spiel (int s, int v, int figur, Graphics g) {
    schrittzahl = s;
    geschwindigkeit = v;
    Gitter gitter = new Gitter(g);
    switch(figur){
      case 1:
        gitter.pentomino1();
        break;
      case 2:
        gitter.pentomino2();
        break;
      case 3:
        gitter.gleiter();
        break;
      case 4:
        gitter.raumschiff();
        break;
      case 5:
        gitter.siebenerreihe();
        break;
    }
    for (int i = 0; i < schrittzahl; i++) {
      gitter.ausgabe();
      g.setColor(Color.black);
      g.drawString(i+"",2,12);
      Gitter gitterNeu = gitter.uebergang(g);
      gitter = gitterNeu;
      gitter.warte(geschwindigkeit);
      gitter.loeschen();
    }
    gitter.ausgabe();
  } // Ende Konstruktor
  
} // Ende Spiel
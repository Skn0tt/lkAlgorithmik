import ch.aplu.turtle.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 14.09.2017
  * @author B. Reichelt
  */
public class KochkurveGUI extends JFrame {
  // Anfang Attribute
  private Playground playground1 = new Playground();
  private Turtle turtle = new Turtle();
  private JButton jbtnKockkurve = new JButton();
  private JLabel jlblTiefe = new JLabel();
  private JNumberField jnbfTiefe = new JNumberField();
  private JLabel lblLaenge = new JLabel();
  private JNumberField jnbfLaenge = new JNumberField();
  private JButton jbtnClear = new JButton();
  private JButton btnSave = new JButton();
  private JButton jbtnSchneeflocke = new JButton();
  private JButton jbtnDrachenkurve = new JButton();
  private JButton jbtnPythagorasBaum = new JButton();

  // Ende Attribute
  private KochkurveGUI(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 975;
    int frameHeight = 645;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    playground1.setBounds(24, 24, 913, 465);
    playground1.setOpaque(false);
    playground1.setBackground(Color.WHITE);
    cp.add(playground1);
    playground1.add(turtle);
    turtle.setBounds(384, 216, 20, 26);
    jbtnKockkurve.setBounds(24, 552, 129, 41);
    jbtnKockkurve.setText("Kochkurve");
    jbtnKockkurve.setMargin(new Insets(2, 2, 2, 2));
    jbtnKockkurve.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jbtnKockkurve_ActionPerformed(evt);
        }
      });
    cp.add(jbtnKockkurve);
    jlblTiefe.setBounds(176, 512, 110, 20);
    jlblTiefe.setText("Rekursionstiefe:");
    cp.add(jlblTiefe);
    jnbfTiefe.setBounds(296, 512, 41, 25);
    jnbfTiefe.setText("3");
    jnbfTiefe.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jnbfTiefe);
    lblLaenge.setBounds(360, 512, 51, 25);
    lblLaenge.setText("Länge:");
    cp.add(lblLaenge);
    jnbfLaenge.setBounds(424, 512, 57, 25);
    jnbfLaenge.setText("800");
    jnbfLaenge.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jnbfLaenge);
    jbtnClear.setBounds(856, 504, 81, 41);
    jbtnClear.setText("Clear");
    jbtnClear.setMargin(new Insets(2, 2, 2, 2));
    jbtnClear.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jbtnClear_ActionPerformed(evt);
        }
      });
    cp.add(jbtnClear);
    btnSave.setBounds(760, 504, 89, 41);
    btnSave.setText("Save");
    btnSave.setMargin(new Insets(2, 2, 2, 2));
    btnSave.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnSave_ActionPerformed(evt);
        }
      });
    cp.add(btnSave);
    jbtnSchneeflocke.setBounds(296, 552, 129, 41);
    jbtnSchneeflocke.setText("Schneeflocke");
    jbtnSchneeflocke.setMargin(new Insets(2, 2, 2, 2));
    jbtnSchneeflocke.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jbtnSchneeflocke_ActionPerformed(evt);
        }
      });
    cp.add(jbtnSchneeflocke);
    jbtnDrachenkurve.setBounds(160, 552, 129, 41);
    jbtnDrachenkurve.setText("Drachenkurve");
    jbtnDrachenkurve.setMargin(new Insets(2, 2, 2, 2));
    jbtnDrachenkurve.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jbtnDrachenkurve_ActionPerformed(evt);
        }
      });
    cp.add(jbtnDrachenkurve);
    jbtnPythagorasBaum.setBounds(432, 552, 129, 41);
    jbtnPythagorasBaum.setText("Pythagoras Baum");
    jbtnPythagorasBaum.setMargin(new Insets(2, 2, 2, 2));
    jbtnPythagorasBaum.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jbtnPythagorasBaum_ActionPerformed(evt);
        }
      });
    cp.add(jbtnPythagorasBaum);
    // Ende Komponenten
    setVisible(true);

    turtle.setPos(0, 0);
    turtle.heading(90);
    turtle.hideTurtle();
  } // end of public KochkurveGUI_neu

  // Anfang Methoden
  public static void main(String[] args) {
    new KochkurveGUI("KochkurveGUI");
  } // end of main

  //Zeichenmethoden
  private void koch(int t, double laenge) {
    if (t == 1) {
      turtle.forward(laenge);
      return;
    }

    koch(t - 1, laenge / 3);
    turtle.left(60);
    koch(t - 1, laenge / 3);
    turtle.right(2* 60);
    koch(t - 1, laenge / 3);
    turtle.left(60);
    koch(t - 1, laenge / 3);
  }

  private void schneeflocke(int t, double laenge) {
    for (int i = 0; i < 3; i++) {
      koch(t, laenge);
      turtle.right(120);
    }
  }

  private void pythagorasBaum(int t, int vz, double laenge) {
    if (t == 1) return;

    turtle.forward(laenge);

    turtle.left(45 * vz);
    pythagorasBaum(t - 1, vz, neueLaenge(laenge));
    turtle.right((45 + 90) * vz);

    turtle.forward(laenge);

    turtle.left((90 + 45) * vz);
    pythagorasBaum(t - 1, vz * -1, laenge);
    turtle.right((90 + 45 + 90) * vz);

    turtle.forward(laenge);
    turtle.right(90 * vz);
    turtle.forward(laenge);
  }

  private double neueLaenge(double a) {
    return Math.sqrt(2 * Math.pow(a, 2));
  }

  private void quadrat(double laenge) {
    for (int i = 0; i < 4; i++) {
      turtle.forward(laenge);
      turtle.left(90);
    }
  }

  //Leitprogramm Rekursives Programmieren. Kapitel 4 Programmieraufgabe 6
  private void drachen(int t, int vz, int laenge) {
    if (t == 0) {
      turtle.forward(laenge);
      return;
    }

    drachen(t - 1, 1, laenge);
    turtle.left(90 * vz);
    drachen(t - 1, -1, laenge);
  }

  private boolean saveWindowTo(String file, int x, int y, int width, int height) {
    try {
      Rectangle windowRect = new Rectangle(x, y, width, height);
      Robot robot = new Robot();
      BufferedImage image = robot.createScreenCapture(windowRect);

      return ImageIO.write(image, "jpg", new File(file));
    } catch (AWTException | IOException e) {
      return false;
    }
  }

  //Löscht den Inhalt der Playground
  private void jbtnClear_ActionPerformed(ActionEvent evt) {
    playground1.clear();
  } // end of jbtnClear_ActionPerformed

  private void btnSave_ActionPerformed(ActionEvent evt) {
    int t = jnbfTiefe.getInt();
    int laenge = jnbfLaenge.getInt();
    Point position = getLocationOnScreen();
    saveWindowTo("Kochkurve " + t + "-" + laenge + ".jpg",
                 (int) position.getX() + 25, (int) position.getY() + 50,
                 playground1.getWidth(), playground1.getHeight());
  } // end of btnSave_ActionPerformed

  private void jbtnKockkurve_ActionPerformed(ActionEvent evt) {
    //Auslesen der Eingabefelder für die Rekursionstiefe und die Länge
    int t = jnbfTiefe.getInt();
    double laenge = jnbfLaenge.getDouble();

    //Turtle Positionieren
    turtle.setPos(-400, -150);
    turtle.setAngle(90);

    koch(t, laenge);
  } // end of jbtnKockkurve_ActionPerformed

  private void jbtnSchneeflocke_ActionPerformed(ActionEvent evt) {
    //Auslesen der Eingabefelder für die Rekursionstiefe und die Länge
    int t = jnbfTiefe.getInt();
    double laenge = jnbfLaenge.getDouble();

    //Turtle Positionieren
    turtle.setPos(-400, -150);
    turtle.setAngle(90);

    schneeflocke(t, laenge);
  } // end of jbtnSchneeflocke_ActionPerformed

  private void jbtnDrachenkurve_ActionPerformed(ActionEvent evt) {
    //Auslesen der Eingabefelder für die Rekursionstiefe und die Länge
    int t = jnbfTiefe.getInt();
    int laenge = jnbfLaenge.getInt();

    //Turtle Positionieren
    turtle.setPos(0, 0);
    turtle.setAngle(90);

    drachen(t, 1, laenge);
  } // end of jbtnDrachenkurve_ActionPerformed

  private void jbtnPythagorasBaum_ActionPerformed(ActionEvent evt) {
    //Auslesen der Eingabefelder für die Rekursionstiefe und die Länge
    int t = jnbfTiefe.getInt();
    double laenge = jnbfLaenge.getDouble();

    //Turtle Positionieren
    turtle.setPos(0, -150);
    turtle.setAngle(0);

    pythagorasBaum(t, 1, laenge);
  } // end of jbtnPythagorasBaum_ActionPerformed

  // Ende Methoden
} // end of class KochkurveGUI_neu

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 31.08.2015
  * @author
  */
public class GUI extends JFrame {
  // Anfang Attribute
  private JLabel jLabel1 = new JLabel();
  private JTextArea jtxtAusgabe = new JTextArea("");
  private JScrollPane jtxtAusgabeScrollPane = new JScrollPane(jtxtAusgabe);
  private JButton btnSuche = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JNumberField jnbfZahl = new JNumberField();
  private JButton btnErzeugeArray = new JButton();
  private BinSuche s = new BinSuche();
  private JLabel lblAusgabe = new JLabel();
  private JLabel jlblNBild = new JLabel();
  private ImageIcon kreuz = new ImageIcon("images/Kreuz.PNG");
  private ImageIcon haken = new ImageIcon("images/Haken.PNG");

  // Ende Attribute
  public GUI(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 513;
    int frameHeight = 396;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jLabel1.setBounds(176, 16, 187, 33);
    jLabel1.setText("Binäre Suche");
    jLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel1);
    jtxtAusgabeScrollPane.setBounds(8, 56, 481, 177);
    jtxtAusgabe.setEnabled(true);
    jtxtAusgabe.setEditable(false);
    jtxtAusgabe.setTabSize(3);
    jtxtAusgabe.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(jtxtAusgabeScrollPane);
    btnSuche.setBounds(120, 240, 81, 49);
    btnSuche.setText("Suche");
    btnSuche.setMargin(new Insets(2, 2, 2, 2));
    btnSuche.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnSuche_ActionPerformed(evt);
        }
      });
    btnSuche.setEnabled(false);
    cp.add(btnSuche);
    jLabel2.setBounds(208, 248, 43, 33);
    jLabel2.setText("Zahl:");
    jLabel2.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(jLabel2);
    jnbfZahl.setBounds(256, 248, 41, 33);
    jnbfZahl.setText("");
    jnbfZahl.setFont(new Font("Dialog", Font.PLAIN, 16));
    jnbfZahl.setEnabled(false);
    jnbfZahl.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jnbfZahl);
    btnErzeugeArray.setBounds(8, 240, 105, 49);
    btnErzeugeArray.setText("Erzeuge Werte");
    btnErzeugeArray.setMargin(new Insets(2, 2, 2, 2));
    btnErzeugeArray.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          btnErzeugeArray_ActionPerformed(evt);
        }
      });
    cp.add(btnErzeugeArray);
    lblAusgabe.setBounds(16, 296, 395, 49);
    lblAusgabe.setText("");
    lblAusgabe.setHorizontalAlignment(SwingConstants.LEFT);
    lblAusgabe.setFont(new Font("Dialog", Font.BOLD, 14));
    cp.add(lblAusgabe);
    jlblNBild.setBounds(424, 288, 60, 60);
    jlblNBild.setText("");
    jlblNBild.setIcon(null);
    cp.add(jlblNBild);
    // Ende Komponenten
    setVisible(true);
  } // end of public BinaereSuche

  // Anfang Methoden
  public static void main(String[] args) {
    new GUI("BinaereSuche");
  } // end of main

  public void btnSuche_ActionPerformed(ActionEvent evt) {
    //TODO
  } // end of btnSuche_ActionPerformed

  public void btnErzeugeArray_ActionPerformed(ActionEvent evt) {
    s.erzeugeNeuesArray(40, 0, 100);
    jtxtAusgabe.setText(s.arrayAusgeben());
    lblAusgabe.setText("");
    jnbfZahl.setInt(0);
    jlblNBild.setIcon(null);
    btnSuche.setEnabled(true);
    jnbfZahl.setEnabled(true);
  } // end of btnErzeugeArray_ActionPerformed

  // Ende Methoden
} // end of class BinaereSuche

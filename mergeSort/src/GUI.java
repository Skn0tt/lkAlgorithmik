import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;


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
  private JTextArea jtxtUnsortiert = new JTextArea("");
  private JScrollPane jtxtUnsortiertScrollPane = new JScrollPane(jtxtUnsortiert);

  private JButton btnMergesort = new JButton();
  private JButton btnQuicksort = new JButton();
  private JButton btnSelectionsort = new JButton();
  private JButton btnBubblesort = new JButton();

  private RekursivSortieren rekursivSortieren = new RekursivSortieren();

  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JTextArea jtxtSortiert = new JTextArea("");
  private JScrollPane jtxtSortiertScrollPane = new JScrollPane(jtxtSortiert);
  private JButton btnWerteErzeugen = new JButton();
  // Ende Attribute

  private GUI(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    int frameWidth = 513; 
    int frameHeight = 619;
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
    jLabel1.setText("Sortieren");
    jLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel1);
    jtxtUnsortiertScrollPane.setBounds(16, 88, 441, 129);
    jtxtUnsortiert.setEnabled(true);
    jtxtUnsortiert.setEditable(false);
    jtxtUnsortiert.setTabSize(3);
    jtxtSortiert.setTabSize(3);
    jtxtUnsortiert.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(jtxtUnsortiertScrollPane);

    // mergeSort
    btnMergesort.setBounds(136, 384, 81, 49);
    btnMergesort.setText("Mergesort");
    btnMergesort.setMargin(new Insets(2, 2, 2, 2));
    btnMergesort.addActionListener(this::btnMergesort_ActionPerformed);
    cp.add(btnMergesort);

    // quickSort
    btnQuicksort.setBounds(136, 384, 81, 49);
    btnQuicksort.setText("Quicksort");
    btnQuicksort.setMargin(new Insets(2, 2, 2, 2));
    btnQuicksort.addActionListener(this::btnQuicksort_ActionPerformed);
    cp.add(btnQuicksort);

    // selectionSort
    btnSelectionsort.setBounds(136, 384, 81, 49);
    btnSelectionsort.setText("Quicksort");
    btnSelectionsort.setMargin(new Insets(2, 2, 2, 2));
    btnSelectionsort.addActionListener(this::btnSelectionsort_ActionPerformed);
    cp.add(btnSelectionsort);

    jLabel2.setBounds(16, 64, 91, 25);
    jLabel2.setText("unsortiert:");
    cp.add(jLabel2);
    jLabel3.setBounds(16, 224, 123, 17);
    jLabel3.setText("sortiert:");
    cp.add(jLabel3);
    jtxtSortiertScrollPane.setBounds(16, 248, 441, 129);
    jtxtSortiert.setFont(new Font("Dialog", Font.BOLD, 16));
    jtxtSortiert.setEditable(false);
    cp.add(jtxtSortiertScrollPane);
    btnWerteErzeugen.setBounds(16, 384, 113, 49);
    btnWerteErzeugen.setText("Erzeuge Werte");
    btnWerteErzeugen.setMargin(new Insets(2, 2, 2, 2));
    btnWerteErzeugen.addActionListener(this::btnWerteErzeugen_ActionPerformed);
    cp.add(btnWerteErzeugen);
    // Ende Komponenten
    setVisible(true);
  } // end of public RekursivSortieren
  
  // Anfang Methoden
  public static void main(String[] args) {
    new GUI("RekursivSortieren");
  } // end of main

  public void btnQuicksort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.quickSort();

    jtxtSortiert.setText(rekursivSortieren.arrayAusgeben());
  } // end of btnMergesort_ActionPerformed

  public void btnBubblesort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.bubbleSort();

    jtxtSortiert.setText(rekursivSortieren.arrayAusgeben());
  } // end of btnMergesort_ActionPerformed

  public void btnSelectionsort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.selectionSort();

    jtxtSortiert.setText(rekursivSortieren.arrayAusgeben());
  } // end of btnMergesort_ActionPerformed
  
  private void btnMergesort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.sort();

    jtxtSortiert.setText(rekursivSortieren.arrayAusgeben());
  } // end of btnMergesort_ActionPerformed

  private void btnWerteErzeugen_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.erzeugeNeuesArray(50, 0, 100);

    jtxtUnsortiert.setText(rekursivSortieren.arrayAusgeben());
  } // end of btnWerteErzeugen_ActionPerformed

  // Ende Methoden
} // end of class BinaereSuche

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
  private JTextArea jtxtUnsortiert = new JTextArea("");
  private JTextArea jtxtSortiert = new JTextArea("");

  private RekursivSortieren rekursivSortieren = new RekursivSortieren();

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
    JLabel jLabel1 = new JLabel();
    jLabel1.setBounds(176, 16, 187, 33);
    jLabel1.setText("Sortieren");
    jLabel1.setFont(new Font("Dialog", Font.BOLD, 24));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel1);
    JScrollPane jtxtUnsortiertScrollPane = new JScrollPane(jtxtUnsortiert);
    jtxtUnsortiertScrollPane.setBounds(16, 88, 441, 129);
    jtxtUnsortiert.setEnabled(true);
    jtxtUnsortiert.setEditable(false);
    jtxtUnsortiert.setTabSize(3);
    jtxtSortiert.setTabSize(3);
    jtxtUnsortiert.setFont(new Font("Dialog", Font.BOLD, 16));
    cp.add(jtxtUnsortiertScrollPane);

    JLabel jLabel2 = new JLabel();
    jLabel2.setBounds(16, 64, 91, 25);
    jLabel2.setText("unsortiert:");
    cp.add(jLabel2);
    JLabel jLabel3 = new JLabel();
    jLabel3.setBounds(16, 224, 123, 17);
    jLabel3.setText("sortiert:");
    cp.add(jLabel3);
    JScrollPane jtxtSortiertScrollPane = new JScrollPane(jtxtSortiert);
    jtxtSortiertScrollPane.setBounds(16, 248, 441, 129);
    jtxtSortiert.setFont(new Font("Dialog", Font.BOLD, 16));
    jtxtSortiert.setEditable(false);
    cp.add(jtxtSortiertScrollPane);
    JButton btnWerteErzeugen = new JButton();
    btnWerteErzeugen.setBounds(16, 384, 113, 49);
    btnWerteErzeugen.setText("Erzeuge Werte");
    btnWerteErzeugen.setMargin(new Insets(2, 2, 2, 2));
    btnWerteErzeugen.addActionListener(this::btnWerteErzeugen_ActionPerformed);
    cp.add(btnWerteErzeugen);

    // mergeSort
    JButton btnMergesort = new JButton();
    btnMergesort.setBounds(250, 450, 100, 50);
    btnMergesort.setText("Mergesort");
    btnMergesort.setMargin(new Insets(2, 2, 2, 2));
    btnMergesort.addActionListener(this::btnMergesort_ActionPerformed);
    cp.add(btnMergesort);

    // quickSort
    JButton btnQuicksort = new JButton();
    btnQuicksort.setBounds(150, 450, 100, 50);
    btnQuicksort.setText("Quicksort");
    btnQuicksort.setMargin(new Insets(2, 2, 2, 2));
    btnQuicksort.addActionListener(this::btnQuicksort_ActionPerformed);
    cp.add(btnQuicksort);

    // selectionSort
    JButton btnSelectionsort = new JButton();
    btnSelectionsort.setBounds(250, 400, 100, 50);
    btnSelectionsort.setText("Selection Sort");
    btnSelectionsort.setMargin(new Insets(2, 2, 2, 2));
    btnSelectionsort.addActionListener(this::btnSelectionsort_ActionPerformed);
    cp.add(btnSelectionsort);

    // bubbleSort
    JButton btnBubblesort = new JButton();
    btnBubblesort.setBounds(150, 400, 100, 50);
    btnBubblesort.setText("Bubble Sort");
    btnBubblesort.setMargin(new Insets(2, 2, 2, 2));
    btnBubblesort.addActionListener(this::btnBubblesort_ActionPerformed);
    cp.add(btnBubblesort);

    // Ende Komponenten
    setVisible(true);
  }

  public static void main(String[] args) {
    new GUI("RekursivSortieren");
  } // end of main

  private void btnQuicksort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.quickSort();

    updateOutput();
  }

  private void btnBubblesort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.bubbleSort();

    updateOutput();
  }

  private void btnSelectionsort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.selectionSort();

    updateOutput();
  }
  
  private void btnMergesort_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.mergeSort();

    updateOutput();
  }

  private void btnWerteErzeugen_ActionPerformed(ActionEvent evt) {
    rekursivSortieren.erzeugeNeuesArray(50, 0, 100);

    updateInput();
  }

  private void updateInput() {
    jtxtUnsortiert.setText(rekursivSortieren.arrayAusgeben());
  }

  private void updateOutput() {
    jtxtSortiert.setText(rekursivSortieren.arrayAusgeben());
  }
}

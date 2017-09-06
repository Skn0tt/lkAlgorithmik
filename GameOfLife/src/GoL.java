import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


public class GoL extends JFrame {
  // Anfang Attribute
  private JPanel jPanel1 = new JPanel(null, true);
  private JTextField jTextField1 = new JTextField("50");
  private JTextField jTextField2 = new JTextField("100");
  private JButton jButton1 = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private int feldbreite = 20;
  private int[][] zustand = new int[30][30];
  private JList jList1 = new JList();
  private DefaultListModel jList1Model = new DefaultListModel();
  private JScrollPane jList1ScrollPane = new JScrollPane(jList1);
  private JLabel jLabel3 = new JLabel();

  // Ende Attribute
  public GoL(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    int frameWidth = 830; 
    int frameHeight = 650;
    setSize(frameWidth, frameHeight);

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);

    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    jPanel1.setBounds(210, 10, 600, 600);
    jPanel1.setOpaque(true);
    jPanel1.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK,
                                                       Color.WHITE));
    jPanel1.setBackground(Color.WHITE);
    cp.add(jPanel1);
    jTextField1.setBounds(128, 56, 62, 20);
    cp.add(jTextField1);
    jTextField2.setBounds(128, 88, 62, 20);
    cp.add(jTextField2);
    jButton1.setBounds(56, 256, 75, 25);
    jButton1.setText("Starten");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          jButton1_ActionPerformed(evt);
        }
      });
    jButton1.setBackground(new Color(0xC0C0C0));

    jButton1.setForeground(Color.BLACK);
    cp.add(jButton1);
    jLabel1.setBounds(8, 56, 110, 20);
    jLabel1.setText("Anzahl der Zyklen:");
    cp.add(jLabel1);
    jLabel2.setBounds(8, 88, 110, 20);
    jLabel2.setText("Geschwindigkeit:");
    cp.add(jLabel2);

    jPanel1.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
          GoL_MouseClicked(evt);
        }
      });
    addWindowListener(new WindowAdapter() {
        public void windowOpened(WindowEvent evt) {
          GoL_WindowOpened(evt);
        }
      });
    jList1.setModel(jList1Model);
    jList1ScrollPane.setBounds(24, 120, 150, 124);
    jList1Model.addElement("Eigenes Spiel");
    jList1Model.addElement("Pentomino1");
    jList1Model.addElement("Pentomino2");
    jList1Model.addElement("Gleiter");
    jList1Model.addElement("Raumschiff");
    jList1Model.addElement("Siebenerreihe");
    jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jList1.setSelectedIndex(0);
    cp.add(jList1ScrollPane);
    jLabel3.setBounds(8, 8, 182, 28);
    jLabel3.setText("Game of Life");
    jLabel3.setFont(new Font("Calibri", Font.BOLD, 20));
    jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
    cp.add(jLabel3);
    cp.setBackground(Color.WHITE);
    // Ende Komponenten
    setVisible(true);
  } // end of public GoL

  // Anfang Methoden
  public void jButton1_ActionPerformed(ActionEvent evt) {
    int schritte = Integer.parseInt(jTextField1.getText());
    int geschw = Integer.parseInt(jTextField2.getText());
    zeichneGitter(jPanel1.getGraphics());

    if (jList1.getSelectedIndex() == 0) {
      Spiel spiel = new Spiel(schritte, geschw, jPanel1.getGraphics(), zustand);
      zustand = new int[30][30];
    } else {
      Spiel spiel = new Spiel(schritte, geschw, jList1.getSelectedIndex(),
                              jPanel1.getGraphics());
    }
  } // end of jButton1_ActionPerformed

  public void zeichneGitter(Graphics zeichnung) {
    zeichnung.setColor(Color.BLACK);

    for (int i = 0; i < (jPanel1.getWidth() / feldbreite); i++) {
      zeichnung.drawLine(i * feldbreite, 0, i * feldbreite, jPanel1.getHeight());
    }

    for (int i = 0; i < (jPanel1.getHeight() / feldbreite); i++) {
      zeichnung.drawLine(0, i * feldbreite, jPanel1.getWidth(), i * feldbreite);
    }
  }

  public void GoL_MouseClicked(MouseEvent evt) {
    // TODO hier Quelltext einfügen
    jList1.setSelectedIndex(0);

    int x = evt.getX() / feldbreite;
    int y = evt.getY() / feldbreite;
    Graphics grafik = jPanel1.getGraphics();

    if (zustand[y][x] == 1) {
      grafik.setColor(Color.white);
      zustand[y][x] = 0;
    } // end of if
    else {
      grafik.setColor(Color.blue);
      zustand[y][x] = 1;
    } // end of if-else

    grafik.fillRect((x * feldbreite) + 2, (y * feldbreite) + 2, feldbreite - 4,
                    feldbreite - 4);
  } // end of GoL_MouseClicked

  public void GoL_WindowOpened(WindowEvent evt) {
    // TODO hier Quelltext einfügen
    zeichneGitter(jPanel1.getGraphics());
  } // end of GoL_WindowOpened

  // Ende Methoden
  public static void main(String[] args) {
    GoL gol = new GoL("GoL");
  } // end of main
} // end of class GoL

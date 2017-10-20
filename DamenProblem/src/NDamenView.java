import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.SwingWorker;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class NDamenView extends JFrame implements NDamenModel.Zuhoerer {
  
  private JButton[][] feld;
  private NDamenModel model;
  private NDamenView view;
  
  private static final Color HELL = Color.WHITE;
  private static final Color DUNKEL = Color.BLACK;
  private static final Color HELL_BEDROHT = new Color(255, 220, 220);
  private static final Color DUNKEL_BEDROHT = new Color(60, 0, 0);
  
  private static final ImageIcon DAME_HELL = new ImageIcon("dame_schwarz_weiss40.gif");
  private static final ImageIcon DAME_DUNKEL = new ImageIcon("dame_weiss_schwarz40.gif");
  private static final ImageIcon BEDROHT_HELL = new ImageIcon("rot_weiss15.gif");
  private static final ImageIcon BEDROHT_DUNKEL = new ImageIcon("rot_schwarz15.gif");
  
  // private ActionListener feldListener = 
  
  public NDamenView(int n) { 
    super("n-Damen-Problem");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    generiereMenu();
    generiereBrett(n);
    pack();
    setResizable(false);
    model = new NDamenModel(n, this);
    view = this;
    setVisible(true);
  }
  
  private void generiereMenu() {
    JMenuBar bar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem menuItem;
    
    menuItem = new JMenuItem("n wählen");
    menuItem.addActionListener(event -> {
      int n = Integer.parseInt(JOptionPane.showInputDialog("Wähle n aus [1..12]:"));
      if (n>0 && n<129) {
        generiereBrett(n);
        pack();
        model = new NDamenModel(n, view);
      }
    });
    menu.add(menuItem);
    
    menuItem = new JMenuItem("Brett leeren");
    menuItem.addActionListener(event -> model.entferneAlleDamen(view));
    menu.add(menuItem);
    
    menuItem = new JMenuItem("Lösung suchen");
    menuItem.addActionListener(event -> new NDamenBacktracker().execute());
    menu.add(menuItem);
    
    menuItem = new JMenuItem("alle Lösungen suchen");
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

      }
    } );
    menu.add(menuItem);
    
    bar.add(menu);
    setJMenuBar(bar);
  }
  
  private void generiereBrett(int n) {
    Container cp = getContentPane();
    cp.removeAll();
    JPanel brett = new JPanel(new GridLayout(n, n));
    JButton button;
    feld = new JButton[n][n];
    for (int y=n-1; y>=0; y--) {
      for (int x=0; x<n; x++) {
        button = new JButton();
        button.setActionCommand("" + x + " " + y);
        button.addActionListener(new ActionListener() { 
          public void actionPerformed(ActionEvent event) { 
            String[] token = event.getActionCommand().split(" "); // "\\s+"
            int x = Integer.parseInt(token[0]);
            int y = Integer.parseInt(token[1]);
            if(!model.entferneDame(x, y, view)) model.setzeDame(x, y, view);
          }
        });
        button.setPreferredSize(new Dimension(60, 60));
        button.setBackground((x+y)%2==0 ? HELL : DUNKEL);
        button.setBorderPainted(false);
        feld[x][y] = button;
        brett.add(button);
      }
    }
    cp.add(brett, "Center");
  }
  
  @Override public void info(NDamenChange change) {
    int x = change.getPos().getX();  
    int y = change.getPos().getY();  
    switch(change.getTyp()) {
      case NDamenChange.DAME_GESETZT:
      feld[x][y].setIcon((x+y)%2==0 ? DAME_HELL : DAME_DUNKEL);
      break;
      case NDamenChange.NEUE_BEDROHUNG:
      feld[x][y].setIcon((x+y)%2==0 ? BEDROHT_HELL : BEDROHT_DUNKEL);
      break;
      case NDamenChange.DAME_ENTFERNT:
      case NDamenChange.KEINE_BEDROHUNG:
      feld[x][y].setIcon(null);
      break;
    }
  }
  
  class NDamenBacktracker extends SwingWorker<Pos[], NDamenChange> {
    
    @Override public Pos[] doInBackground() {
      
      final class SwingZuhoerer implements NDamenModel.Zuhoerer {
        @Override public void info(NDamenChange change) { publish(change); }
      }
      
      return model.backtrack(new SwingZuhoerer());
    }
    
    @Override protected void process(List<NDamenChange> changes) {
      for (NDamenChange change: changes) {
        view.info(change);
      }
    }
    
    @Override protected void done() {
    }
  }
  
  public static void main(String[] args) {
    NDamenView view = new NDamenView(8);
  }
  
  
  
}

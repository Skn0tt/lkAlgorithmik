import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;


public class FieldLabel extends JPanel {
  
  private static final int CANDIDATE_SIZE = 20;
  
  private int x;
  private int y;
  private boolean conflict;
  private JLabel valueLabel = new JLabel("", JLabel.CENTER);
  private JPanel candidatePanel = new JPanel(new GridLayout(3,3));
  private JButton[] candidateButtons = new JButton[10];
  private CardLayout cardLayout = new CardLayout();
  
  
  public FieldLabel(int x, int y) {
    this.x = x;
    this.y = y;
    conflict = false;
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    this.setLayout(cardLayout);
    
    valueLabel.setPreferredSize(new Dimension(CANDIDATE_SIZE * 3, CANDIDATE_SIZE * 3));
    valueLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, CANDIDATE_SIZE * 2));
    valueLabel.setOpaque(false);
    this.add(valueLabel, "VALUE");
    
    for (int i=1; i<10; i++) {
      candidateButtons[i] = new JButton(""+i);
      candidateButtons[i].setPreferredSize(new Dimension(CANDIDATE_SIZE, CANDIDATE_SIZE));
      candidateButtons[i].setBorderPainted(false);
      candidateButtons[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, CANDIDATE_SIZE *2 / 3));
      candidateButtons[i].setFocusable(false);
      candidateButtons[i].setOpaque(false);
      candidatePanel.add(candidateButtons[i]);
    }
    this.add(candidatePanel, "CANDIDATES");
  }
  
  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }
  
  public boolean getConflict() {
    return conflict;
  }
  
  public void setValue(String valueString) {
    valueLabel.setText(valueString);
    cardLayout.show(this, "VALUE");
  }

}
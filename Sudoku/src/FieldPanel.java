import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;


public class FieldPanel extends JPanel {
  
  private static final Color BACKGROUND_COLOR1 = new Color(200, 200, 200);
  private static final Color BACKGROUND_COLOR2 = new Color(220, 220, 220);
  private static final Color CONFLICT_COLOR = Color.RED;
  private static final Color FOCUS_COLOR = Color.YELLOW;
  private static final String CANDIDATES = "CANDIDATES";
  private static final String VALUE = "VALUE";
  private static final boolean FOCUS = true;
  private static final boolean NO_FOCUS = false;
  
  private FieldModel model;
  private int x, y;
  private JLabel valueLabel = new JLabel("", JLabel.CENTER);
  private JPanel candidatePanel = new JPanel(new GridLayout(SudokuModel.SUB_SIZE, SudokuModel.SUB_SIZE));
  private CandidateButton[] candidateButtons = new CandidateButton[SudokuModel.SIZE+1];
  private CardLayout cardLayout = new CardLayout();
  private String state = CANDIDATES;
  private boolean focus = false;
  private boolean autoCandidates = true;
  
  
  public FieldPanel(FieldModel model) {
    this.model = model;
    x = model.getX(); y = model.getY();
    this.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    this.setLayout(cardLayout);
    
    candidatePanel.setOpaque(false);
    for (int i=1; i<=SudokuModel.SIZE; i++) {
      candidateButtons[i] = new CandidateButton(i);
      candidatePanel.add(candidateButtons[i]);
    }
    this.add(candidatePanel, CANDIDATES);
    
    valueLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, CandidateButton.CANDIDATE_SIZE * 2));
    valueLabel.setOpaque(false);
    this.add(valueLabel, VALUE);
    
    updateBackground();
  }
  
  public void setFocus(boolean newFocus) {
    focus = newFocus;
    updateBackground();
  }
  
  public void setValue(int newValue) {
    if (newValue != FieldModel.NO_VALUE) {
      valueLabel.setText(""+newValue);
      showValue();
    }
    else {
      valueLabel.setText("");
      if (autoCandidates) showCandidates(); else showValue();
    }
  }
  
  public void setCandidateState(int candidate, boolean newState) {
    candidateButtons[candidate].setState(newState);
  }
  
  public void toggleCandidateState(int candidate) {
    setCandidateState(candidate, !candidateButtons[candidate].getState());  
  }
  
  public void showValue() {
    cardLayout.show(this, state = VALUE);
  }
  
  public void showCandidates() {
    cardLayout.show(this, state = CANDIDATES);
  }
  
  public void refreshFromModel() {
    int value = model.getValue();
    valueLabel.setText(value==0 ? "" : ""+value);
    for (int candidate=1; candidate<=SudokuModel.SIZE; candidate++) {
      candidateButtons[candidate].setState(model.isCandidatePossible(candidate));
    }
    updateBackground();
    if (value == 0 && autoCandidates) showCandidates();
    else showValue(); 
  }
  
  /*
  public void updateCandidatesFromModel() {
  int[] valuesInDependents = model.getValuesInDependents();
  for (int candidate=1; candidate < valuesInDependents.length; candidate++) {
  candidateButtons[candidate].setState(valuesInDependents[candidate] == 0);
  }
  }
  */
  
  public void updateBackground() {
    Color bgColor = (x/3+y/3)%2==0 ? BACKGROUND_COLOR1 : BACKGROUND_COLOR2;
    if (model.isConflict()) bgColor = CONFLICT_COLOR;
    if (focus) bgColor = FOCUS_COLOR;
    setBackground(bgColor);
  }
  
  public void setAutoCandidates(boolean autoCandidates) {
    this.autoCandidates = autoCandidates;
    refreshFromModel();
  }
  
}                       

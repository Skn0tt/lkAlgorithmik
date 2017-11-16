import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SudokuPanel extends JPanel implements SudokuListener {
  
  private FieldPanel[][] fieldPanels = new FieldPanel[SudokuModel.SIZE][SudokuModel.SIZE]; 
  private JPanel[][] subsquarePanels = new JPanel[SudokuModel.SUB_SIZE][SudokuModel.SUB_SIZE];      
  private int focusX, focusY;
  private SudokuModel model;
  private boolean autoCandidates = true; 
  
  public SudokuPanel(SudokuModel model) {
    super(new GridLayout(SudokuModel.SUB_SIZE, SudokuModel.SUB_SIZE));
    this.model = model;
    for (int y = 0; y < SudokuModel.SUB_SIZE; y++) {
      for (int x = 0; x < SudokuModel.SUB_SIZE; x++) {
        subsquarePanels[x][y] = new JPanel(new GridLayout(SudokuModel.SUB_SIZE, SudokuModel.SUB_SIZE));
        subsquarePanels[x][y].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        this.add(subsquarePanels[x][y]);
      }
    }
    
    for (int y = 0; y < SudokuModel.SIZE; y++) {
      for (int x = 0; x < SudokuModel.SIZE; x++) {
        fieldPanels[x][y] = new FieldPanel(model.getField(x, y));
        subsquarePanels[x / SudokuModel.SUB_SIZE][y / SudokuModel.SUB_SIZE].add(fieldPanels[x][y]);
      }
    }
    moveFocusTo(4, 4);
  }
  
  public void moveFocusTo(int x, int y) {
    fieldPanels[focusX][focusY].setFocus(false);
    focusX = x; focusY = y;
    fieldPanels[x][y].setFocus(true);
  }
  
  public void moveFocusBy(int deltaX, int deltaY) {
    int x = focusX+deltaX, y = focusY+deltaY;
    if (x >= 0 && y >= 0 && x < 9 && y < 9) moveFocusTo(x, y);
  }
  
  public void setValueOfFocus(int newValue) {
    setValue(focusX, focusY, newValue);
  }
  
  private void setValue(int x, int y, int newValue) {
    model.setFieldValue(x, y, newValue, this, SudokuModel.NO_DELAY);
    // fieldPanels[x][y].showValue();
  }
  
  public void showCandidatesOfFocus() {
    if (model.getField(focusX, focusY).getValue() == FieldModel.NO_VALUE) {
      fieldPanels[focusX][focusY].refreshFromModel();
      fieldPanels[focusX][focusY].showCandidates();
    }
  }
  
  public void showValueOfFocus() {
    fieldPanels[focusX][focusY].showValue();
  }
  
  public void showAllCandidates() {
    for (int y = 0; y < SudokuModel.SIZE; y++) {
      for (int x = 0; x < SudokuModel.SIZE; x++) {
        if (model.getField(x,y).getValue() == FieldModel.NO_VALUE) {
          // fieldPanels[x][y].updateCandidatesFromModel();
          fieldPanels[x][y].showCandidates();
        }
      }
    }
  }
  
  public void showNoCandidates() {
    for (int y = 0; y < SudokuModel.SIZE; y++) {
      for (int x = 0; x < SudokuModel.SIZE; x++) {
        fieldPanels[x][y].showValue();
      }
    }
  }
  
  public void refreshFromModel() {
    for (int y = 0; y < SudokuModel.SIZE; y++) {
      for (int x = 0; x < SudokuModel.SIZE; x++) {
        fieldPanels[x][y].refreshFromModel();
      }
    }
  }
  
  public void toggleCandidateOfFocus(int candidate) {
    if (model.getField(focusX, focusY).getValue() == FieldModel.NO_VALUE) {
      fieldPanels[focusX][focusY].toggleCandidateState(candidate);
      fieldPanels[focusX][focusY].showCandidates();
    }
  }
  
  public void setAutoCandidates(boolean autoCandidates) {
    this.autoCandidates = autoCandidates;
    for (int y = 0; y < SudokuModel.SIZE; y++) {
      for (int x = 0; x < SudokuModel.SIZE; x++) {
        fieldPanels[x][y].setAutoCandidates(autoCandidates);
      }
    }
  }
  
  public void info(SudokuChange change) {
    int x = change.getX();
    int y = change.getY();
    switch (change.getType()) {
      case SudokuChange.NEW_VALUE: {
        fieldPanels[x][y].setValue(change.getValue());
        break;
      }
      case SudokuChange.CONFLICT_CHANGED: {
        fieldPanels[x][y].updateBackground();
        break;
      }
      case SudokuChange.CANDIDATE_CHANGED: {
        if (autoCandidates) fieldPanels[x][y].setCandidateState(change.getCandidate(), change.getCandidateState());
        break;
      }
    }  
  }
  
}
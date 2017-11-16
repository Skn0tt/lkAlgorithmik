import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

public class CandidateButton extends JButton {
  
  public static final int CANDIDATE_SIZE = 800 / SudokuModel.SIZE / SudokuModel.SUB_SIZE;
  public static final Color POSSIBLE_COLOR = Color.BLACK;
  public static final Color IMPOSSIBLE_COLOR = Color.WHITE;
  public static final boolean IMPOSSIBLE = false;
  public static final boolean POSSIBLE = true;
  
  private int candidate;
  private boolean state = POSSIBLE;
  
  public CandidateButton(int candidate) {
    super(""+candidate);
    setPreferredSize(new Dimension(CANDIDATE_SIZE, CANDIDATE_SIZE));
    setMargin(new Insets(0, 0, 0, 0));
    setBorder(null);
    setFont(new Font(Font.SANS_SERIF, Font.PLAIN, CANDIDATE_SIZE / 2));
    setFocusable(false);
    setOpaque(false);
    setContentAreaFilled(false);
  }
  
  public int getCandidate() {
    return candidate;
  }
  
  public boolean getState() {
    return state;
  }
  
  public void setState(boolean newState) {
    if (state != newState) {
      state = newState;
      setForeground(state ? POSSIBLE_COLOR : IMPOSSIBLE_COLOR);
    }
  }  
  
}  
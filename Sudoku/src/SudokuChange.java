public class SudokuChange {
  
  public static final int NEW_VALUE = 0;
  public static final int CONFLICT_CHANGED = 1;
  public static final int CANDIDATE_CHANGED = 2;
  public static final boolean NEW_CONFLICT = true;
  public static final boolean NO_CONFLICT = false;
  
  private int type = -1;
  private int x = 0;
  private int y = 0;
  private int value = 0;
  private boolean isConflict = false;
  private int candidate = 0;
  private boolean candidateState = false;
  
  public SudokuChange(int x, int y, int value) {
    this.type = NEW_VALUE;
    this.x = x;
    this.y = y;
    this.value = value;
  }
  
  public SudokuChange(int x, int y, boolean isConflict) {
    this.type = CONFLICT_CHANGED;
    this.x = x;
    this.y = y;
    this.isConflict = isConflict;
  }
  
  public SudokuChange(int x, int y, int candidate, boolean candidateState) {
    this.type = CANDIDATE_CHANGED;
    this.x = x;
    this.y = y;
    this.candidate = candidate;
    this.candidateState = candidateState;
  }
  
  public int getType() {
    return type;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getValue() {
    return value;
  }
  
  public boolean isConflict() {
    return isConflict;
  }
  
  public int getCandidate() {
    return candidate;
  }
  
  public boolean getCandidateState() {
    return candidateState;
  }
  
}
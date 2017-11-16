public class FieldModel {
  
  public static final int NO_VALUE = 0;
  private static final boolean CANDIDATE_POSSIBLE = true;
  private static final boolean CANDIDATE_IMPOSSIBLE = false;
  
  private int x;
  private int y;
  private int value = NO_VALUE;
  private FieldModel[] dependents;
  private int[] valueInDependents; // number of dependents with the value
  private int numberOfCandidates;
  
  public FieldModel(int x, int y, int numberOfPossibleValues) {
    this.x = x; this.y = y;
    valueInDependents = new int[numberOfPossibleValues+1]; // initially 0 for all
    this.numberOfCandidates = numberOfPossibleValues;
  }
  
  public int setValue(int newValue, SudokuListener listener) {
    int deltaConflict = 0;
    int oldValue = value;
    for (FieldModel dependent : dependents) {
      deltaConflict += dependent.valueInDependentRemoved(oldValue, listener);
      deltaConflict += dependent.valueInDependentAdded(newValue, listener);
    }
    boolean isOldConflict = isConflict();
    value = newValue;
    listener.info(new SudokuChange(x, y, newValue));
    if (isConflict() != isOldConflict) {
      listener.info(new SudokuChange(x, y, isConflict()));
      deltaConflict += isConflict() ? 1 : -1;
    }
    return deltaConflict;
  }
  
  public int getValue() {
    return value;
  }
  
  private int valueInDependentRemoved(int dependentValue, SudokuListener listener) {
    if (dependentValue == NO_VALUE) return 0;
    valueInDependents[dependentValue]--;
    if (valueInDependents[dependentValue] == 0) {
      numberOfCandidates++;
      listener.info(new SudokuChange(x, y, dependentValue, CANDIDATE_POSSIBLE));
      if (value == dependentValue) {
        listener.info(new SudokuChange(x, y, SudokuChange.NO_CONFLICT));
        return -1; // one conflict less
      }
    }
    return 0; // no change of number of conflicts
  }
  
  private int valueInDependentAdded(int dependentValue, SudokuListener listener) {
    if (dependentValue == NO_VALUE) return 0;
    valueInDependents[dependentValue]++;
    if (valueInDependents[dependentValue] == 1) {
      numberOfCandidates--;
      listener.info(new SudokuChange(x, y, dependentValue, CANDIDATE_IMPOSSIBLE));
      if (value == dependentValue) {
        listener.info(new SudokuChange(x, y, SudokuChange.NEW_CONFLICT));
        return 1; // one conflict more
      }
    }
    return 0; // no change of number of conflicts
  }
  
  /*
  public int[] getValuesInDependents() {
  return valueInDependents;
  }
  */
  public boolean isCandidatePossible(int candidate) {
    return valueInDependents[candidate] == 0;
  }
  
  public int getNumberOfCandidates() {
    return numberOfCandidates;
  }
  
  public int[] getCandidates() {
    int[] candidates = new int[numberOfCandidates];
    int i=0;
    for (int candidate = 1; candidate<valueInDependents.length; candidate++) {
      if (valueInDependents[candidate] == 0) candidates[i++] = candidate;
    }
    return candidates;
  }
  
  public boolean isConflict() {
    if (value == NO_VALUE) return false;
    return valueInDependents[value] != 0;
  }
  
  public void setDependents(FieldModel[] dependents) {
    this.dependents = dependents;
  }
  
  public Pos getPos() {
    return new Pos(x, y);
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
}
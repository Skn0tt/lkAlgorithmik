import java.util.Random;

public class SudokuModel {
  
  public static final int SUB_SIZE = 3; // size of a small square
  public static final int SIZE = SUB_SIZE * SUB_SIZE; // size of the full square
  public static final String SEPARATOR = ",";  // for String representation of a puzzle
  public static final boolean WITH_GUESSING = true; // for readability: parameter for new puzzle creation
  public static final boolean WITHOUT_GUESSING = false;
  public static final int NO_DELAY = 0; // for readability
  
  private FieldModel[][] fields = new FieldModel[SIZE][SIZE]; // Sudoku consists of this square of fields
  private int numberOfFieldsWithValues = 0; // empty puzzle initially with no values
  private int numberOfConflicts = 0; // fields with an other field in conflict
  
  public SudokuModel() {
    for (int x = 0; x < SIZE; x++) {
      for (int y = 0; y < SIZE; y++) {
        fields[x][y] = new FieldModel(x, y, SIZE);
      }
    }
    for (int x = 0; x < SIZE; x++) {
      for (int y = 0; y < SIZE; y++) {
        fields[x][y].setDependents(getDependents(x, y));
      }
    }
  }
  
  public FieldModel getField(int x, int y) {
    return fields[x][y];
  }
  
  public void setFieldValue(int x, int y, int newValue, SudokuListener listener, int millisecDelay) {
    int oldValue = fields[x][y].getValue();
    if (oldValue == newValue) {
      listener.info(new SudokuChange(x, y, newValue)); // needs to be informed as 
      return;
    }    
    if (newValue == FieldModel.NO_VALUE) numberOfFieldsWithValues--;
    if (oldValue == FieldModel.NO_VALUE) numberOfFieldsWithValues++;
    numberOfConflicts += fields[x][y].setValue(newValue, listener);
  }
  
  private FieldModel[] getDependents(int x, int y) {
    FieldModel[] dependents = new FieldModel[20];
    int iField = 0;
    int baseX = (x/SUB_SIZE)*SUB_SIZE;
    int baseY = (y/SUB_SIZE)*SUB_SIZE;
    for (int i=0; i<SIZE; i++) {
      if (i!=x) dependents[iField++] = fields[i][y];
      if (i!=y) dependents[iField++] = fields[x][i];
      int subSquareX = baseX + i%SUB_SIZE;
      int subSquareY = baseY + i/SUB_SIZE;
      if (subSquareX != x && subSquareY != y) {
        dependents[iField++] = fields[subSquareX][subSquareY];
      }
    }
    return dependents;
  }
  
  public String toString() {
    StringBuilder sudoku = new StringBuilder("" + SUB_SIZE);
    for (int x=0; x<SIZE; x++) {
      for (int y=0; y<SIZE; y++) {
        sudoku.append(SEPARATOR).append(fields[x][y].getValue());
      }
    }
    return sudoku.toString();
  }  
  
  public void fromString(String sudoku, SudokuListener listener) {
    String[] valueStrings = sudoku.split(SEPARATOR);
    int i = 1;
    for (int x=0; x<SIZE; x++) {
      for (int y=0; y<SIZE; y++) {
        setFieldValue(x, y, Integer.parseInt(valueStrings[i++]), listener, NO_DELAY);
      }
    }
  }
  
  public void emptyPuzzle(SudokuListener listener) {
    for (int x=0; x<SIZE; x++) {
      for (int y=0; y<SIZE; y++) {
        setFieldValue(x, y, FieldModel.NO_VALUE, listener, NO_DELAY);
      }
    }
  }
  
  public boolean isSolution() {
    return numberOfFieldsWithValues == SIZE*SIZE;
  }
  
}
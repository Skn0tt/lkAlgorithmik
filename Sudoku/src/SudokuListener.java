public interface SudokuListener {
  
  public class NoListener implements SudokuListener {
    public void info(SudokuChange change) {
    }
  }  
  
  public static final SudokuListener NO_LISTENER = new NoListener();
  
  public void info(SudokuChange change);
  
}

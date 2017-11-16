public interface SudokuListener {
  
  class NoListener implements SudokuListener {
    public void info(SudokuChange change) {
    }
  }  
  
  SudokuListener NO_LISTENER = new NoListener();
  
  void info(SudokuChange change);
  
}

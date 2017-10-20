public class NDamenChange {
  
  public static final int DAME_GESETZT = 0;
  public static final int DAME_ENTFERNT = 1;
  public static final int NEUE_BEDROHUNG = 2;
  public static final int KEINE_BEDROHUNG = 3;
  
  private int typ;
  private Pos pos;
  
  public NDamenChange(int typ, Pos pos) {
    this.typ = typ;
    this.pos = pos;
  }
  
  public int getTyp() {
    return typ;
  }
  
  public Pos getPos() {
    return pos;
  }
  
}
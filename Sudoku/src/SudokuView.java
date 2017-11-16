import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 26.10.2017
  * @author Knut Leiß
  */

public class SudokuView extends JFrame {
  
  public static final int FIND_ONE_SOLUTION_IMPROVED = 0;
  public static final int FIND_ONE_SOLUTION = 1;
  public static final int IS_SOLUTION_WITHOUT_GUESSING = 2;
  public static final int IS_UNIQUE_SOLUTION = 3;
  public static final int IS_UNIQUE_SOLUTION_IMPROVED = 4;
  public static final int COUNT_ALL_SOLUTIONS = 5;
  public static final int COUNT_ALL_SOLUTIONS_IMPROVED = 6;
  public static final int RANDOM_PUZZLE_FROM_SOLUTION = 7;
  public static final int RANDOM_PUZZLE_FROM_EMPTY = 8;
  
  private SudokuModel model = new SudokuModel();
  private SudokuView view = this;
  private SudokuPanel sudokuPanel = new SudokuPanel(model);
  private JLabel statusLabel = new JLabel(" ");
  private JFileChooser fileChooser = new JFileChooser();
  private FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Sudoku Game Files", "sdk");
  private JMenuItem displayAllCandidatesMenuItem;
  
  private boolean autoCandidates = true;
  
  public SudokuView() { 
    super("Sudoku Creator & Solver");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fileChooser.setFileFilter(fileFilter);
    
    JPanel contentPane = (JPanel) getContentPane();
    addKeyBindings(contentPane);
    contentPane.add(sudokuPanel, "Center");
    // sudokuPanel.showAllCandidates();
    
    statusLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 13));
    contentPane.add(statusLabel, "South");
    
    setJMenuBar(createMenuBar());
    
    pack();
    setResizable(false);
    setVisible(true);
  }
  
  public void setAutoCandidates(boolean autoCandidates) {
    this.autoCandidates = autoCandidates;
    sudokuPanel.setAutoCandidates(autoCandidates);
    displayAllCandidatesMenuItem.setEnabled(!autoCandidates);
  }
  
  private void addKeyBindings(JPanel contentPane) {
    KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
      e -> {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
          int keyCode = e.getKeyCode();
          if (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9) {
            if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) == KeyEvent.CTRL_DOWN_MASK) {
              sudokuPanel.setValueOfFocus(keyCode - KeyEvent.VK_0);
              return true;
            }
          }
          switch (keyCode) {
            case KeyEvent.VK_RIGHT: sudokuPanel.moveFocusBy(1, 0); return true;
            case KeyEvent.VK_LEFT: sudokuPanel.moveFocusBy(-1, 0); return true;
            case KeyEvent.VK_UP: sudokuPanel.moveFocusBy(0, -1); return true;
            case KeyEvent.VK_DOWN: sudokuPanel.moveFocusBy(0, 1); return true;
            case KeyEvent.VK_DELETE: {
              sudokuPanel.setValueOfFocus(FieldModel.NO_VALUE);
              return true;
            }
          }
        }

        if (e.getID() == KeyEvent.KEY_TYPED) {
          char keyChar = e.getKeyChar();
          if (keyChar >= '1' && keyChar <= '9' && !autoCandidates) {
            sudokuPanel.toggleCandidateOfFocus(keyChar - '0');
            return true;
          }
          if (keyChar == '?' && !autoCandidates) {
            sudokuPanel.showCandidatesOfFocus();
            return true;
          }
        }
        return false;
      });
  }  
  
  private JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenuItem menuItem;
    
    JMenu puzzleMenu = new JMenu("Puzzle");
    puzzleMenu.setMnemonic(KeyEvent.VK_P);
    
    menuItem = new JMenuItem("Generate solved puzzle (trivial)", KeyEvent.VK_T);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
    menuItem.setEnabled(false);
    menuItem.addActionListener(event -> {
      //TODO: Implement
    });
    puzzleMenu.add(menuItem);
    
    menuItem = new JMenuItem("Generate random puzzle (from solution)", KeyEvent.VK_P);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    menuItem.setEnabled(false);
    menuItem.addActionListener(event -> {
      if (!model.isSolution()) {
        JOptionPane.showMessageDialog(view, "There is no solution present.\nCreate One first.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    });
    puzzleMenu.add(menuItem);
    
    menuItem = new JMenuItem("Empty puzzle", KeyEvent.VK_E);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(event -> model.emptyPuzzle(sudokuPanel));
    puzzleMenu.add(menuItem);
    
    menuItem = new JMenuItem("Load puzzle", KeyEvent.VK_L);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        int returnValue = fileChooser.showDialog(view, "Load Sudoku puzzle");
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File file = fileChooser.getSelectedFile();
          if (file.canRead()) {
            try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
              String gameString = reader.readLine();
              model.fromString(gameString, sudokuPanel);
              if (!autoCandidates) sudokuPanel.showNoCandidates();
            } catch (IOException x) {
              System.err.format("IOException: %s%n", x);
            }
          }
        }
      }
    } );
    puzzleMenu.add(menuItem);
    
    menuItem = new JMenuItem("Save puzzle", KeyEvent.VK_S);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(event -> {
      int returnValue = fileChooser.showDialog(view, "Save Sudoku puzzle");
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
          String gameString = model.toString();
          writer.write(gameString);
        } catch (IOException x) {
          System.err.format("IOException: %s%n", x);
        }
      }
    });
    puzzleMenu.add(menuItem);
    
    puzzleMenu.addSeparator();
    
    menuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(event -> System.exit(0));
    puzzleMenu.add(menuItem);
    
    JMenu solveMenu = new JMenu("Solve");
    solveMenu.setMnemonic(KeyEvent.VK_S);
    
    displayAllCandidatesMenuItem = new JMenuItem("Display all candidates", KeyEvent.VK_D);
    displayAllCandidatesMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
    displayAllCandidatesMenuItem.setEnabled(false);
    displayAllCandidatesMenuItem.addActionListener(event -> {
      if (!autoCandidates) sudokuPanel.showAllCandidates();
    });
    solveMenu.add(displayAllCandidatesMenuItem);
    
    menuItem = new JMenuItem("Check if solution without guessing", KeyEvent.VK_G);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
    menuItem.setEnabled(false);
    menuItem.addActionListener(event -> {
      //TODO: Implement
    });
    solveMenu.add(menuItem);
    
    menuItem = new JMenuItem("Find one solution", KeyEvent.VK_F);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
    menuItem.setEnabled(false);
    menuItem.addActionListener(event -> {
      //TODO: Implement
    });
    solveMenu.add(menuItem);
    
    menuItem = new JMenuItem("Check if unique solution", KeyEvent.VK_U);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
    menuItem.setEnabled(false);
    menuItem.addActionListener(event -> {
      //TODO: Implement
    });
    solveMenu.add(menuItem);
    
    menuItem = new JMenuItem("Count all solutions", KeyEvent.VK_C);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    menuItem.setEnabled(false);
    menuItem.addActionListener(event -> {
      //TODO: Implement
    });
    solveMenu.add(menuItem);
    
    JMenu helpMenu = new JMenu("Help");
    helpMenu.setMnemonic(KeyEvent.VK_H);
    
    menuItem = new JMenuItem("Sudoku game rules", KeyEvent.VK_R);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(event ->
      JOptionPane.showMessageDialog(
        view,
        "Fill all fields with values (1..9),\nso that no value occurs more than once\n(a) in each row\n(b) in each column\n(c) in each " +
          SudokuModel.SUB_SIZE +
          "x" +
          SudokuModel.SUB_SIZE +
          " small square.",
        "Sudoku Rules",
        JOptionPane.INFORMATION_MESSAGE
      )
    );
    helpMenu.add(menuItem);
    
    menuItem = new JMenuItem("Keyboard controls", KeyEvent.VK_K);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(event ->
      JOptionPane.showMessageDialog(
        view,
        "Arrow keys: Change focus (yellow field).\n\n<1..9>: Toggle candiate on/off.\n\n<CTRL>+<1..9>: Set field value.\n\n<DEL>: Delete candiates or value.\n\n?: Show all valid candiates.\n\nBeware: Candidates can only be edited, if option 'Auto-adjust candidates' is off.",
        "Keyboard Controls",
        JOptionPane.INFORMATION_MESSAGE
      )
    );
    helpMenu.add(menuItem);
    
    menuItem = new JMenuItem("Info on program", KeyEvent.VK_I);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
    menuItem.addActionListener(event ->
      JOptionPane.showMessageDialog(
        view,
        "Sudoku Creator & Solver\n\nCreated in Nov 2017\nfor a computer science class\non backpropagation\nby Knut Leiß, Germany.\n\nInfo: schule@knut-leiss.de",
        "About this program",
        JOptionPane.INFORMATION_MESSAGE
      )
    );
    helpMenu.add(menuItem);
    
    JMenu optionsMenu = new JMenu("Options");
    helpMenu.setMnemonic(KeyEvent.VK_O);
    
    menuItem = new JCheckBoxMenuItem("Auto-adjust candidates", true);
    menuItem.addItemListener(event -> setAutoCandidates(event.getStateChange() == ItemEvent.SELECTED));
    optionsMenu.add(menuItem);
    
    menuBar.add(puzzleMenu);
    menuBar.add(solveMenu);
    menuBar.add(helpMenu);
    menuBar.add(optionsMenu);
    
    return(menuBar);
  }
  
  public static void main(String[] args) {
    try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
    catch (Exception ex) { ex.printStackTrace(); }
    new SudokuView();
  }
}


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuView extends JFrame
{
    SudokuModel model;
    public final int SUDOKU_SIZE = 9;
    public final int SMALL_SQUARE_SIZE = 3;
    NumberView[][] numbers;
    JPanel numbersPanel = new JPanel();
    JButton setButton = new JButton("set");
    JButton clearButton = new JButton("clear");
    JButton solveButton = new JButton("solve");
    
    public SudokuView(SudokuModel model)
    {
        this.model = model;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setSize(300,350);
        this.setLayout(new BorderLayout());
        
        this.add(numbersPanel, BorderLayout.CENTER);
        numbersPanel.setLayout(new GridLayout(9,9));
        numbers = new NumberView[SUDOKU_SIZE][SUDOKU_SIZE];
        for (int i=0;i<SUDOKU_SIZE;i++)
            for (int j=0;j<SUDOKU_SIZE;j++)
            {
                numbers[i][j] = new NumberView(i,j);
                numbers[i][j].setHorizontalAlignment(JTextField.CENTER);
                numbersPanel.add(numbers[i][j]);
            }
        for (int i=0;i<SUDOKU_SIZE;i++)
            for (int j=0;j<SUDOKU_SIZE;j++) 
                if ((i/SMALL_SQUARE_SIZE + j/SMALL_SQUARE_SIZE) %2 == 0)
                    numbers[i][j].setBackground(Color.LIGHT_GRAY);
        
        JPanel buttonsPanel = new JPanel();
        this.add(buttonsPanel, BorderLayout.SOUTH);
        buttonsPanel.add(setButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(solveButton);
        
        showNumbers();
        this.setVisible(true);
    }
    
    public void showNumbers()
    {
        int[][] matrix = model.getNumbers();
        for (int i=0; i<matrix.length;i++)
            for (int j=0; j<matrix[i].length;j++)
            {
                if (matrix[i][j] != 0)
                    numbers[i][j].setText(matrix[i][j]+"");
                else
                    numbers[i][j].setText("");
                numbers[i][j].getCaret().setVisible(false);
            }
        
    }
    
    public void setNumbers()
    {
        for (int i=0;i<numbers.length;i++)
            for (int j=0; j<numbers[i].length;j++)
                numbers[i][j].setSet();
    }
    
    public void clearNumbers()
    {
        for (int i=0;i<numbers.length;i++)
            for (int j=0; j<numbers[i].length;j++)
                {
                    numbers[i][j].setText("");
                    numbers[i][j].setEditable(true);
                    numbers[i][j].setForeground(Color.BLACK);
                }        
        this.showNumbers();
    }
    
    
    public void addEnterListener(KeyListener el)
    {
        for (int i=0;i<numbers.length;i++)
            for (int j=0; j<numbers[i].length;j++)
                numbers[i][j].addKeyListener(el);
    }
    
    public void addFocusListener(FocusListener fl)
    {
        for (int i=0;i<numbers.length;i++)
            for (int j=0; j<numbers[i].length;j++)
                numbers[i][j].addFocusListener(fl);
    }
    
    
    
    public void addSetListener(ActionListener sl)
    {
     setButton.addActionListener(sl);
    }   
    
    
    public void addClearListener(ActionListener cl)
    {
     clearButton.addActionListener(cl);
    } 
    
    public void addSolveListener(ActionListener cl)
    {
     solveButton.addActionListener(cl);
    }   
}



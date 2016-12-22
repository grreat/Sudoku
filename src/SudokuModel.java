
import java.util.ArrayList;

public class SudokuModel
{
    private final int SUDOKU_SIZE = 9;
    private final int INITIAL_VALUE = 0;
    private NumberModel[][] matrix;
    public SudokuModel()
    {
        matrix = new NumberModel[SUDOKU_SIZE][SUDOKU_SIZE];
        for (int i=0;i<SUDOKU_SIZE;i++)
            for (int j=0;j<SUDOKU_SIZE;j++)
                matrix[i][j] = new NumberModel();
        
    }
    public void printSudoku()
    {
        for (int i=0; i<SUDOKU_SIZE;i++)
        {
            
            for (int j=0; j<SUDOKU_SIZE;j++)
                System.out.print(matrix[i][j].getNumber()+" ");
            System.out.println();
        }
    }
    
    public boolean isSudokuLegal()
    {
        for (int i=0;i<SUDOKU_SIZE;i++)
            if (!isRowLegal(i))
            {
//                System.out.println("row "+i+" is not legal");
                return false;
            }
        for (int i=0;i<SUDOKU_SIZE;i++)
            if (!isColumnLegal(i))
            {
//                System.out.println("column "+i+" is not legal");
                return false;
            }
        for (int i=0;i<SUDOKU_SIZE;i=i+3)   
            for (int j=0;j<SUDOKU_SIZE;j=j+3)
            {
                if (!isSquareLegal(i,j))
                    {
                        
//                        System.out.println("Square starting at "+i+", "+j+" is not legal");
                        return false;
                    }
            }
        return true;
    }

    public boolean isNumberLegal(int row, int column)
    {
        if (!isRowLegal(row))
            return false;
        if (!isColumnLegal(column))
            return false;
        if (!isSquareLegal(row, column))
            return false;
        return true;
    }
    
    
    public boolean isRowLegal(int row)
    {
        for (int i=0; i<SUDOKU_SIZE;i++)
            for (int j=i+1; j<SUDOKU_SIZE;j++)
                if ((matrix[row][i].getNumber()!=0) && (matrix[row][i].getNumber()==matrix[row][j].getNumber()))
                        return false;
        return true;
    }
    
    public boolean isColumnLegal(int column)
    {
        for (int i=0; i<SUDOKU_SIZE;i++)
            for (int j=i+1; j<SUDOKU_SIZE;j++)
                if ((matrix[i][column].getNumber()!=0) && (matrix[i][column].getNumber()==matrix[j][column].getNumber()))
                        return false;
        return true;
    }
    
    public boolean isSquareLegal(int row, int column) //row and column of begining of squares
    {
        ArrayList<Integer> list = new ArrayList();
        int startRow=(row/3)*3;
        int startColumn=(column/3)*3;
        for (int i=startRow; i<startRow+3;i++)
            for (int j=(startColumn/3)*3; j<startColumn+3; j++)
                if (matrix[i][j].getNumber()!=0)
                    list.add(matrix[i][j].getNumber());
        return isListLegal(list);
    }
    
    public boolean isListLegal(ArrayList<Integer> list)
    {
        list.sort(null);
        int i=0;
        while (i+1<list.size())
        {
            if (list.get(i)==list.get(i+1))
                return false;
            i++;
        }
        return true;
    }
    
    public int[][] getNumbers()
    {
        int[][] toReturn = new int[SUDOKU_SIZE][SUDOKU_SIZE];
        for (int i=0;i<SUDOKU_SIZE;i++)
            for (int j=0;j<SUDOKU_SIZE;j++)
                toReturn[i][j] = matrix[i][j].getNumber();
        return toReturn;
    }
    
    public boolean setNumber(int i, int j, int number)
    {
        if ((number<0) || (number>9))
            return false;
        matrix[i][j].setNumber(number);
        if (isNumberLegal(i,j))
            {
                System.out.println("number is legal");
                return true;
            }
        else
            {
                System.out.println("number is not legal");
                matrix[i][j].setNumber(0);
                return false;
            }
    }
    
    
    
    public void clearNumbers()
    {
        for (int i=0;i<matrix.length;i++)
            for (int j=0;j<matrix[i].length;j++)
                matrix[i][j].setNumber(0);
    }
    
    public boolean isSolved()
    {
        for (int i=0;i<matrix.length;i++)
            for (int j=0;j<matrix[i].length;j++)
                if (matrix[i][j].getNumber() == 0)
                    return false;
        return true;
    }
    

    public boolean solve()
    {
        
        return solve(0,0);
    }
    
    private boolean solve(int i, int j) {
        if (i == 9)
            {
                i = 0;
                if (++j == 9)
                    return true;
            }
        if (matrix[i][j].getNumber() != 0)
            return solve(i+1,j);

        for (int val = 1; val <= 9; ++val)
        {
            if (setNumber(i,j,val))
            {
                matrix[i][j].setNumber(val);
                if (solve(i+1,j))
                    return true;
            }
        }
        matrix[i][j].setNumber(0);// reset on backtrack
        return false;
    }
}

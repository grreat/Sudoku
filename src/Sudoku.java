public class Sudoku
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SudokuModel model = new SudokuModel();
        SudokuView view = new SudokuView(model);
        SudokuController controller = new SudokuController(model, view);
//        view.showNumbers();
//        view.setNumbers();
        
         
    }
    
}

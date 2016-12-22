
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SudokuController
{
    SudokuModel model;
    SudokuView view;
    public SudokuController(SudokuModel model, SudokuView view)
    {
        this.model = model;
        this.view = view;
        view.addEnterListener(new EnterListener());
        view.addFocusListener(new UnfocusListener() {});
        view.addSetListener(new SetListener());
        view.addClearListener(new ClearListener());
        view.addSolveListener(new SolveListener());
//        model.solveMatrix();
//        view.showNumbers();
//        System.out.println("test");
    }

    
    private class EnterListener implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            if (e.getKeyChar()==('\n'))
            {
                try 
                {
                    Integer.parseInt(((NumberView)(e.getSource())).getText());
                }
                catch(Exception ex)
                {
                    ((NumberView)(e.getSource())).setText("0");
                }
                model.setNumber(((NumberView)(e.getSource())).getHorizontalIndex(), ((NumberView)(e.getSource())).getVerticalIndex(), Integer.parseInt(((NumberView)(e.getSource())).getText()));
                model.printSudoku();
                view.showNumbers();
            }
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent ke)
        {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    public class UnfocusListener implements FocusListener
    {

        @Override
        public void focusGained(FocusEvent fe)
        {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            if (((NumberView)(e.getSource())).getText().length()!=0)
                {
                    try 
                    {
                        Integer.parseInt(((NumberView)(e.getSource())).getText());
                    }
                    catch(Exception ex)
                    {
                        ((NumberView)(e.getSource())).setText("0");
                    }
                    model.setNumber(((NumberView)(e.getSource())).getHorizontalIndex(), ((NumberView)(e.getSource())).getVerticalIndex(), Integer.parseInt(((NumberView)(e.getSource())).getText()));
                    model.printSudoku();
                    view.showNumbers(); 
                }
        }
        
    }
    
    public class SetListener implements ActionListener
    {
      @Override
      public void actionPerformed(ActionEvent e) 
      {
        view.setNumbers();
      }
    }

    public class ClearListener implements ActionListener
    {
      @Override
      public void actionPerformed(ActionEvent e) 
      {
        model.clearNumbers();
        view.clearNumbers();
      }
    }
    public class SolveListener implements ActionListener
    {
      @Override
      public void actionPerformed(ActionEvent e) 
      {
        if (model.solve())
              System.out.println("done solving");
        else
              System.out.println("could not solve");
        
        view.showNumbers();
//          System.out.println("done solving");
      }
    }
        
    
}

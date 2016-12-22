
import java.awt.Color;
import javax.swing.JTextField;


public class NumberView extends JTextField
{
    private int horizontalIndex;
    private int verticalIndex;
    private boolean set;
    public NumberView(int i, int j)
    {
        horizontalIndex = i;
        verticalIndex = j;
        set = false;
    }
    
    public int getHorizontalIndex()
    {
        return horizontalIndex;
    }
    
    public int getVerticalIndex()
    {
        return verticalIndex;
    }
    
    public void clear()
    {
        set = false;
        setEditable(true);
        setForeground(Color.BLACK);
    }
    
    public void setSet()
    {
        if (!this.getText().equals(""))
        {
            set = true;
            this.setEditable(false);
            this.setForeground(Color.RED);
        }
    }
    
    public boolean getSet()
    {
        return set;
    }
}
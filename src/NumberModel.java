public class NumberModel
{
    private int number;
    private boolean fixed;
    public NumberModel()
    {
        number=0;
        fixed=false;
    }
    
    public void setFixed()
    {
        fixed=true;
    }
    
    public void clearFixed()
    {
        fixed=false;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }
    
    
}

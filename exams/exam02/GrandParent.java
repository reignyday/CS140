package exam02;

import java.util.Arrays;

public class GrandParent extends Parent
{
    private String[] grandChildren;
    
    public GrandParent(String n, String[] c, String[] gc)
    {
        super(n, c);

        if (gc == null)
            throw new IllegalArgumentException("grandchildren array cannot be null");
        
        this.grandChildren = gc;
    }
    
    @Override
    public int familySize()
    {
        return super.familySize() + this.grandChildren.length;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\nGrandchildren: " + Arrays.toString(this.grandChildren);
    }
}

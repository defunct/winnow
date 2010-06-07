package com.goodworkalan.winnow;

// TODO Document.
public class Equals implements Condition
{
    // TODO Document.
    private final Object expected;
    
    // TODO Document.
    public Equals(Object expected)
    {
        this.expected = expected;
    }
    
    // TODO Document.
    public boolean test(Object object)
    {
        if (expected == null)
        {
            return object == null;
        }
        else if (object == null)
        {
            return false;
        }
        return expected.equals(object);
    }
    
    // TODO Document.
    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object instanceof Equals)
        {
            Equals equals = (Equals) object;
            if (expected == null)
            {
                return equals.expected == null;
            }
            else if (equals.expected == null)
            {
                return false;
            }
            return expected.equals(equals.expected);
        }
        return false;
    }
    
    // TODO Document.
    @Override
    public int hashCode()
    {
        return expected == null ? 17 : expected.hashCode();
    }
    
    // TODO Document.
    public String toString()
    {
        return "Equals(" + expected + ")";
    }
}

package com.goodworkalan.deviate;

public class Equals implements Match
{
    private final Object expected;
    
    public Equals(Object expected)
    {
        this.expected = expected;
    }
    
    public boolean match(Object object)
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
    
    @Override
    public int hashCode()
    {
        return expected == null ? 17 : expected.hashCode();
    }
}

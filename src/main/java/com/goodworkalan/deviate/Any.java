package com.goodworkalan.deviate;

// TODO Document.
public class Any implements Condition
{
    // TODO Document.
    public boolean test(Object object)
    {
        return true;
    }
    
    // TODO Document.
    @Override
    public boolean equals(Object object)
    {
        return (object instanceof Any);
    }
    
    // TODO Document.
    @Override
    public int hashCode()
    {
        return 17;
    }
}

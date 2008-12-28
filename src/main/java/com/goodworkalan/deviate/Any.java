package com.goodworkalan.deviate;

public class Any implements Condition
{
    public boolean test(Object object)
    {
        return true;
    }
    
    @Override
    public boolean equals(Object object)
    {
        return (object instanceof Any);
    }
    
    @Override
    public int hashCode()
    {
        return 17;
    }
}

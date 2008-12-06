package com.goodworkalan.deviate;

public class Any implements Match
{
    public boolean match(Object object)
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

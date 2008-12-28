package com.goodworkalan.diverge;

public class Not implements Condition
{
    private final Condition condition;
    
    public Not(Condition condition)
    {
        this.condition = condition;
    }
    
    public boolean test(Object object)
    {
        return !condition.test(object);
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object instanceof Not)
        {
            Not not = (Not) object;
            return condition.equals(not.condition);
        }
        return false;
    }
}

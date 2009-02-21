package com.goodworkalan.diverge;

// TODO Document.
public class Not implements Condition
{
    // TODO Document.
    private final Condition condition;
    
    // TODO Document.
    public Not(Condition condition)
    {
        this.condition = condition;
    }
    
    // TODO Document.
    public boolean test(Object object)
    {
        return !condition.test(object);
    }
    
    // TODO Document.
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

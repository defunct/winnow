package com.goodworkalan.diverge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class And implements Condition
{
    private final Set<Condition> conditions;
    
    public And(Condition...conditions)
    {
        this.conditions = new HashSet<Condition>(Arrays.asList(conditions));
    }
    
    public boolean test(Object object)
    {
        for (Condition condition : conditions)
        {
            if (!condition.test(object))
            {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object instanceof And)
        {
            And and = (And) object;
            return conditions.equals(and.conditions);
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return conditions.hashCode();
    }
}

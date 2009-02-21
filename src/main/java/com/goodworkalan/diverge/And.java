package com.goodworkalan.diverge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// TODO Document.
public class And implements Condition
{
    // TODO Document.
    private final Set<Condition> conditions;
    
    // TODO Document.
    public And(Condition...conditions)
    {
        this.conditions = new HashSet<Condition>(Arrays.asList(conditions));
    }
    
    // TODO Document.
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
    
    // TODO Document.
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
    
    // TODO Document.
    @Override
    public int hashCode()
    {
        return conditions.hashCode();
    }
}

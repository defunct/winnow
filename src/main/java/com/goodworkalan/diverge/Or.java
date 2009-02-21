package com.goodworkalan.diverge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// TODO Document.
public class Or
{
    // TODO Document.
    private final Set<Condition> conditions;
    
    // TODO Document.
    public Or(Condition...conditions)
    {
        this.conditions = new HashSet<Condition>(Arrays.asList(conditions));
    }
    
    // TODO Document.
    public boolean test(Object object)
    {
        for (Condition condition : conditions)
        {
            if (condition.test(object))
            {
                return true;
            }
        }
        return false;
    }
    
    // TODO Document.
    @Override
    public boolean equals(Object object)
    {
        if (object == this)
        {
            return true;
        }
        if (object instanceof Or)
        {
            Or or = (Or) object;
            return conditions.equals(or.conditions);
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

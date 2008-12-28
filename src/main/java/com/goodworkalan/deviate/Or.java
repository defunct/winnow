package com.goodworkalan.deviate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Or
{
    private final Set<Condition> conditions;
    
    public Or(Condition...conditions)
    {
        this.conditions = new HashSet<Condition>(Arrays.asList(conditions));
    }
    
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

    @Override
    public int hashCode()
    {
        return conditions.hashCode();
    }
}

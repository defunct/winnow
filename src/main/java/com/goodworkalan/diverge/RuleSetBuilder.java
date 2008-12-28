package com.goodworkalan.diverge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RuleSetBuilder<T>
{
    private final RuleMapBuilder<T> conditions;
    
    private final Map<Object, Set<Condition>> expression;
    
    public RuleSetBuilder(RuleMapBuilder<T> conditions)
    {
        this.conditions = conditions;
        this.expression = new HashMap<Object, Set<Condition>>();
    }
    
    public RuleBuilder<T> check(Object key, Condition condition)
    {
        if (!expression.containsKey(key))
        {
            expression.put(key, new HashSet<Condition>());
        }
        return new RuleBuilder<T>(conditions, expression, key).or(condition);
    }
}

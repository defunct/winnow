package com.goodworkalan.diverge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Expression<T>
{
    private final RuleMapBuilder<T> conditions;
    
    private final Map<Object, Set<Condition>> expression;
    
    public Expression(RuleMapBuilder<T> conditions)
    {
        this.conditions = conditions;
        this.expression = new HashMap<Object, Set<Condition>>();
    }
    
    public ConditionSet<T> check(Object key, Condition condition)
    {
        expression.put(key, new HashSet<Condition>());
        return new ConditionSet<T>(conditions, expression, key).or(condition);
    }
}

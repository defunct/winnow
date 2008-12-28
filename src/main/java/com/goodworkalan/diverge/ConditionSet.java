package com.goodworkalan.diverge;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConditionSet<T>
{
    private final RuleMapBuilder<T> rules;
 
    private final Object key;
    
    private final Map<Object, Set<Condition>> expression;
    
    public ConditionSet(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression, Object key)
    {
        this.rules = rules;
        this.key = key;
        this.expression = expression;
    }

    public ConditionSet<T> or(Condition condition)
    {
        expression.get(key).add(condition);
        return this;
    }
    
    public ConditionSet<T> check(Object key, Condition condition)
    {
        expression.put(key, new HashSet<Condition>());
        return new ConditionSet<T>(rules, expression, key).or(condition);
    }
    
    public void put(T value)
    {
        rules.put(expression, value);
    }
}

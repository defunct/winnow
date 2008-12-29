package com.goodworkalan.diverge;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RuleSetBuilder<T>
{
    private final RuleMapBuilder<T> rules;
    
    protected final Map<Object, Set<Condition>> expression;
    
    public RuleSetBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression)
    {
        this.rules = rules;
        this.expression = expression;
    }
    
    public RuleBuilder<T> check(Object key, Condition condition)
    {
        if (!expression.containsKey(key))
        {
            expression.put(key, new HashSet<Condition>());
        }
        return new RuleBuilder<T>(rules, expression, key).or(condition);
    }
    
    public void put(T value)
    {
        rules.put(expression, value);
    }
}

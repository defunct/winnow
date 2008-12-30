package com.goodworkalan.diverge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CoreRuleSetBuilder<T> implements RuleSetBuilder<T>
{
    private final RuleMapBuilder<T> rules;
    
    protected final Map<Object, Set<Condition>> expression;
    
    public CoreRuleSetBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression)
    {
        this.rules = rules;
        this.expression = expression;
    }
    
    public RuleSetBuilder<T> duplicate()
    {
        Map<Object, Set<Condition>> newExpression = new HashMap<Object, Set<Condition>>();
        for (Map.Entry<Object, Set<Condition>> entry : expression.entrySet())
        {
            newExpression.put(entry.getKey(), new HashSet<Condition>(entry.getValue()));
        }
        return new CoreRuleSetBuilder<T>(rules, newExpression);
    }

    public RuleBuilder<T> check(Object key, Condition condition)
    {
        if (!expression.containsKey(key))
        {
            expression.put(key, new HashSet<Condition>());
        }
        return new CoreRuleBuilder<T>(rules, expression, key).or(condition);
    }
    
    public void put(T value)
    {
        rules.put(expression, value);
    }
}

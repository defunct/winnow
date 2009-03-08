package com.goodworkalan.deviate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// TODO Document.
public class CoreRuleSetBuilder<T> implements RuleSetBuilder<T>
{
    // TODO Document.
    private final RuleMapBuilder<T> rules;
    
    // TODO Document.
    protected final Map<Object, Set<Condition>> expression;
    
    // TODO Document.
    public CoreRuleSetBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression)
    {
        this.rules = rules;
        this.expression = expression;
    }
    
    // TODO Document.
    public RuleSetBuilder<T> duplicate()
    {
        Map<Object, Set<Condition>> newExpression = new HashMap<Object, Set<Condition>>();
        for (Map.Entry<Object, Set<Condition>> entry : expression.entrySet())
        {
            newExpression.put(entry.getKey(), new HashSet<Condition>(entry.getValue()));
        }
        return new CoreRuleSetBuilder<T>(rules, newExpression);
    }

    // TODO Document.
    public RuleBuilder<T> check(Object key, Condition condition)
    {
        if (!expression.containsKey(key))
        {
            expression.put(key, new HashSet<Condition>());
        }
        return new CoreRuleBuilder<T>(rules, expression, key).or(condition);
    }
    
    // TODO Document.
    public void put(T value)
    {
        rules.put(expression, value);
    }
}

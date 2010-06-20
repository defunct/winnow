package com.goodworkalan.winnow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Build a set of rules to associate with an object.
 * 
 * @author Alan Gutierrez
 * 
 * @param <T>
 *            The type of value object.
 */
public class RuleSetBuilder<T> {
    // TODO Document.
    private final RuleMapBuilder<T> rules;
    
    // TODO Document.
    protected final Map<Object, Set<Condition>> expression;
    
    // TODO Document.
    public RuleSetBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression)
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
        return new RuleSetBuilder<T>(rules, newExpression);
    }

    // TODO Document.
    public RuleBuilder<T> check(Object key, Condition condition)
    {
        if (!expression.containsKey(key))
        {
            expression.put(key, new HashSet<Condition>());
        }
        return new RuleBuilder<T>(rules, expression, key).or(condition);
    }
    
    // TODO Document.
    public void put(T value)
    {
        rules.put(expression, value);
    }
}

package com.goodworkalan.winnow;

import java.util.Map;
import java.util.Set;

// TODO Document.
public class CoreRuleBuilder<T> extends CoreRuleSetBuilder<T> implements RuleBuilder<T>
{
    // TODO Document.
    private final Object key;
    
    // TODO Document.
    public CoreRuleBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression, Object key)
    {
        super(rules, expression);
        this.key = key;
    }

    // TODO Document.
    public RuleBuilder<T> or(Condition condition)
    {
        expression.get(key).add(condition);
        return this;
    }
}

package com.goodworkalan.diverge;

import java.util.Map;
import java.util.Set;

public class CoreRuleBuilder<T> extends CoreRuleSetBuilder<T> implements RuleBuilder<T>
{
    private final Object key;
    
    public CoreRuleBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression, Object key)
    {
        super(rules, expression);
        this.key = key;
    }

    public RuleBuilder<T> or(Condition condition)
    {
        expression.get(key).add(condition);
        return this;
    }
}

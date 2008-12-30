package com.goodworkalan.diverge;

import java.util.List;

public class RuleBuilderList<T> extends RuleSetBuilderList<T> implements RuleBuilder<T>
{
    private final Object key;
    
    public RuleBuilderList(List<RuleSetBuilder<T>> listOfSetOfRules, Object key)
    {
        super(listOfSetOfRules);
        this.key = key;
    }
    
    public RuleBuilder<T> or(Condition condition)
    {
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            setOfRules.check(key, condition);
        }
        return this;
    }
}

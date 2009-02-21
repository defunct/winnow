package com.goodworkalan.diverge;

import java.util.List;

// TODO Document.
public class RuleBuilderList<T> extends RuleSetBuilderList<T> implements RuleBuilder<T>
{
    // TODO Document.
    private final Object key;
    
    // TODO Document.
    public RuleBuilderList(List<RuleSetBuilder<T>> listOfSetOfRules, Object key)
    {
        super(listOfSetOfRules);
        this.key = key;
    }
    
    // TODO Document.
    public RuleBuilder<T> or(Condition condition)
    {
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            setOfRules.check(key, condition);
        }
        return this;
    }
}

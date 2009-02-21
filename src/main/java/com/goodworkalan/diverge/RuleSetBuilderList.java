package com.goodworkalan.diverge;

import java.util.ArrayList;
import java.util.List;

// TODO Document.
public class RuleSetBuilderList<T> implements RuleSetBuilder<T>
{
    // TODO Document.
    public final List<RuleSetBuilder<T>> listOfSetOfRules;
    
    // TODO Document.
    public RuleSetBuilderList(List<RuleSetBuilder<T>> listOfSetOfRules)
    {
        this.listOfSetOfRules = listOfSetOfRules;
    }
    
    // TODO Document.
    public RuleSetBuilder<T> duplicate()
    {
        List<RuleSetBuilder<T>> newList = new ArrayList<RuleSetBuilder<T>>();
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            newList.add(setOfRules.duplicate());
        }
        return new RuleSetBuilderList<T>(newList);
    }
    
    // TODO Document.
    public RuleBuilder<T> check(Object key, Condition condition)
    {
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            setOfRules.check(key, condition);
        }
        return new RuleBuilderList<T>(listOfSetOfRules, key);
    }
    
    // TODO Document.
    public void put(T value)
    {
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            setOfRules.put(value);
        }
    }
}

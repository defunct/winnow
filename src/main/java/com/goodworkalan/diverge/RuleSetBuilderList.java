package com.goodworkalan.diverge;

import java.util.ArrayList;
import java.util.List;

public class RuleSetBuilderList<T> implements RuleSetBuilder<T>
{
    public final List<RuleSetBuilder<T>> listOfSetOfRules;
    
    public RuleSetBuilderList(List<RuleSetBuilder<T>> listOfSetOfRules)
    {
        this.listOfSetOfRules = listOfSetOfRules;
    }
    
    public RuleSetBuilder<T> duplicate()
    {
        List<RuleSetBuilder<T>> newList = new ArrayList<RuleSetBuilder<T>>();
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            newList.add(setOfRules.duplicate());
        }
        return new RuleSetBuilderList<T>(newList);
    }
    
    public RuleBuilder<T> check(Object key, Condition condition)
    {
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            setOfRules.check(key, condition);
        }
        return new RuleBuilderList<T>(listOfSetOfRules, key);
    }
    
    public void put(T value)
    {
        for (RuleSetBuilder<T> setOfRules : listOfSetOfRules)
        {
            setOfRules.put(value);
        }
    }
}

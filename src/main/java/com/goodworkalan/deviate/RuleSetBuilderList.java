package com.goodworkalan.deviate;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an interface I created in order to have a single interface
 * that I could pass in the Paste builder language, that would populate
 * a lot of different sets, or populate the conditions for a handful of
 * different routes that had been split by an or.
 * <p>
 * This is outgiong.
 * <p>
 * Because I can just as easily pass around a list or a list of lists in Paste
 * and this interface won't exsit to confuse me.
 * 
 * @author Alan Gutierrez
 *
 * @param <T> The type conditionally mapped.
 */
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

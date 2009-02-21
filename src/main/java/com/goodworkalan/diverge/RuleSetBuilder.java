package com.goodworkalan.diverge;

// TODO Document.
public interface RuleSetBuilder<T>
{
    // TODO Document.
    public RuleSetBuilder<T> duplicate();
    
    // TODO Document.
    public RuleBuilder<T> check(Object key, Condition condition);
    
    // TODO Document.
    public void put(T value);
}

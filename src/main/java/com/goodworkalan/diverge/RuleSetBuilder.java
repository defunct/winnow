package com.goodworkalan.diverge;

public interface RuleSetBuilder<T>
{
    public RuleSetBuilder<T> duplicate();
    
    public RuleBuilder<T> check(Object key, Condition condition);
    
    public void put(T value);
}

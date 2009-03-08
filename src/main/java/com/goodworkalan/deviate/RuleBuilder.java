package com.goodworkalan.deviate;

// TODO Document.
public interface RuleBuilder<T> extends RuleSetBuilder<T>
{
    // TODO Document.
    public RuleBuilder<T> or(Condition condition);
}

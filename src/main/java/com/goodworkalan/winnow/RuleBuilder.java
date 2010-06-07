package com.goodworkalan.winnow;

// TODO Document.
public interface RuleBuilder<T> extends RuleSetBuilder<T>
{
    // TODO Document.
    public RuleBuilder<T> or(Condition condition);
}

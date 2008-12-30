package com.goodworkalan.diverge;

public interface RuleBuilder<T> extends RuleSetBuilder<T>
{
    public RuleBuilder<T> or(Condition condition);
}

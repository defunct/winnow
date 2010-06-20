package com.goodworkalan.winnow;

import java.util.Map;
import java.util.Set;

// TODO Document.
public class RuleBuilder<T> extends RuleSetBuilder<T> {
	// TODO Document.
    private final Object key;
    
    // TODO Document.
    public RuleBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression, Object key) {
        super(rules, expression);
        this.key = key;
    }

	// TODO Document.
	public RuleBuilder<T> or(Condition condition) {
		expression.get(key).add(condition);
		return this;
    }
}

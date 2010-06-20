package com.goodworkalan.winnow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Build a set of rules to associate with an object. This is easier than
 * building the multi-map by hand. The each client of the library would have to
 * rewrite the test to determine if the set has already been created.
 * 
 * @author Alan Gutierrez
 * 
 * @param <T>
 *            The type of value object.
 */
public class RuleSetBuilder<T> {
    // TODO Document.
    private final RuleMapBuilder<T> rules;
    
    // TODO Document.
    protected final Map<Object, Set<Condition>> expression;
    
    // TODO Document.
    public RuleSetBuilder(RuleMapBuilder<T> rules, Map<Object, Set<Condition>> expression)
    {
        this.rules = rules;
        this.expression = expression;
    }
    
    /**
     * Create a duplicate of this rule set builder that can be modified independently.
     * 
     * @return A duplicate of this rule set.
     */
	public RuleSetBuilder<T> duplicate() {
		Map<Object, Set<Condition>> newExpression = new HashMap<Object, Set<Condition>>();
		for (Map.Entry<Object, Set<Condition>> entry : expression.entrySet()) {
			newExpression.put(entry.getKey(), new HashSet<Condition>(entry.getValue()));
		}
		return new RuleSetBuilder<T>(rules, newExpression);
    }

	/**
	 * Add the given condition to the set of conditions associated with the
	 * given key.
	 * 
	 * @param key
	 *            The key.
	 * @param condition
	 *            The condition to add to the set of conditions.
	 */
	public  RuleSetBuilder<T> check(Object key, Condition condition) {
    	Set<Condition> conditions = expression.get(key);
		if (conditions == null) {
			conditions = new HashSet<Condition>();
			expression.put(key, conditions);
		}
		conditions.add(condition);
		return this;
    }

	// TODO Document.
	public void put(T value) {
        rules.put(expression, value);
    }
}

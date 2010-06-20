package com.goodworkalan.winnow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TODO Document.
public class RuleMapBuilder<T> {
    // TODO Document.
    private final Map<Integer, T> values;
    
    // TODO Document.
    private final Map<Integer, Map<Object, Set<Condition>>> toCondition;
    
    // TODO Document.
    private final Map<Map<Object, Condition>, Set<Integer>> toIdentifier;
    
    /** The next unique identifier for a condition. */
    private int nextIdentifier;
    
    /** Create an empty rule map builder. */
	public RuleMapBuilder() {
        this.values = new HashMap<Integer, T>();
        this.toCondition = new HashMap<Integer, Map<Object,Set<Condition>>>();
        this.toIdentifier = new LinkedHashMap<Map<Object, Condition>, Set<Integer>>();
    }

	/**
	 * Map the given conditions to the given value.
	 * 
	 * @param conditions
	 *            The expression.
	 * @param value
	 *            The value.
	 */
	public void put(Map<Object, Set<Condition>> conditions, T value) {
		Map<Object, Set<Condition>> duplicate = new HashMap<Object, Set<Condition>>();
		for (Map.Entry<Object, Set<Condition>> test : conditions.entrySet()) {
			duplicate.put(test.getKey(), new HashSet<Condition>(test.getValue()));
		}
		int id = nextIdentifier++;
		values.put(id, value);
		toCondition.put(id, conditions);
		for (Map.Entry<Object, Set<Condition>> test : conditions.entrySet()) {
			for (Condition condition : test.getValue()) {
                Map<Object, Condition> key = Collections.singletonMap(test.getKey(), condition);
                Set<Integer> ids = toIdentifier.get(key);
				if (ids == null) {
					ids = new HashSet<Integer>();
					toIdentifier.put(key, ids);
				}
				ids.add(id);
            }
        }
    }
    
    // TODO Document.
	public RuleSetBuilder<T> rule() {
		return new RuleSetBuilder<T>(this, new HashMap<Object, Set<Condition>>());
	}
    
    // TODO Document.
    public RuleMap<T> newRuleMap()
    {
        // Dirty means we've added new conditions. If so, we need to copy
        // them over to our local
        List<Map<Object, Condition>> conditions = new ArrayList<Map<Object, Condition>>(toIdentifier.keySet());
        Collections.sort(conditions,
            new Comparator<Map<Object, Condition>>()
            {
                public int compare(Map<Object, Condition> o1, Map<Object, Condition> o2)
                {
                    return toIdentifier.get(o2).size() - toIdentifier.get(o1).size();
                }
            });
        
        Map<Map<Object, Condition>, Set<Integer>> newIdentifiers = new LinkedHashMap<Map<Object,Condition>, Set<Integer>>();
        for (Map<Object, Condition> test : conditions)
        {
            newIdentifiers.put(test, new HashSet<Integer>(toIdentifier.get(test)));
        }

        Map<Integer, T> newValues = new HashMap<Integer, T>(values);
        
        Map<Integer, Map<Object, Set<Condition>>> newRules = new HashMap<Integer, Map<Object,Set<Condition>>>(toCondition);
        
        return new RuleMap<T>(newValues, newRules, newIdentifiers);
    }
}

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
public class RuleMapBuilder<K, T> {
    /** The map of unique identifiers to object values. */
    private final Map<Integer, T> values;
    
    /** The map of identifiers to the conditions that match them. */
    private final Map<Integer, Map<K, Set<Condition>>> toCondition;
    
    /** The map of conditions to the identifiers they match. */
    private final Map<Map<K, Condition>, Set<Integer>> toIdentifier;
    
    /** The next unique identifier for a condition. */
    private int nextIdentifier;
    
    /** Create an empty rule map builder. */
    public RuleMapBuilder() {
        this.values = new HashMap<Integer, T>();
        this.toCondition = new HashMap<Integer, Map<K, Set<Condition>>>();
        this.toIdentifier = new LinkedHashMap<Map<K, Condition>, Set<Integer>>();
    }

    /**
     * Map the given conditions to the given value.
     * 
     * @param conditions
     *            The expression.
     * @param value
     *            The value.
     */
    public void put(Map<K, Set<Condition>> conditions, T value) {
        Map<K, Set<Condition>> duplicate = new HashMap<K, Set<Condition>>();
        for (Map.Entry<K, Set<Condition>> test : conditions.entrySet()) {
            duplicate.put(test.getKey(), new HashSet<Condition>(test.getValue()));
        }
        int id = nextIdentifier++;
        values.put(id, value);
        toCondition.put(id, conditions);
        for (Map.Entry<K, Set<Condition>> test : conditions.entrySet()) {
            for (Condition condition : test.getValue()) {
                Map<K, Condition> key = Collections.singletonMap(test.getKey(), condition);
                Set<Integer> ids = toIdentifier.get(key);
                if (ids == null) {
                    ids = new HashSet<Integer>();
                    toIdentifier.put(key, ids);
                }
                ids.add(id);
            }
        }
    }

    /**
     * Create a new rule in the rule builder.
     * 
     * @return A rule set builder to specify a rule for the rule map.
     */
    public RuleSetBuilder<K, T> rule() {
        return new RuleSetBuilder<K, T>(this, new HashMap<K, Set<Condition>>());
    }

    /**
     * Create a rule map from the rules defined by this rule builder.
     * <p>
     * The rule map will have a copy of the rule definitions, so that the
     * builder can be used to further specify a subsequent rule map. Not a
     * terribly useful feature, but it is a property of the buidler none the
     * less.
     * 
     * @return A new rule map.
     */
    public RuleMap<K, T> newRuleMap() {
        // Dirty means we've added new conditions. If so, we need to copy
        // them over to our local
        List<Map<K, Condition>> conditions = new ArrayList<Map<K, Condition>>(toIdentifier.keySet());
        Collections.sort(conditions, new Comparator<Map<K, Condition>>() {
            public int compare(Map<K, Condition> o1, Map<K, Condition> o2) {
                return toIdentifier.get(o2).size() - toIdentifier.get(o1).size();
            }
        });
        
        Map<Map<K, Condition>, Set<Integer>> newIdentifiers = new LinkedHashMap<Map<K, Condition>, Set<Integer>>();
        for (Map<K, Condition> test : conditions) {
            newIdentifiers.put(test, new HashSet<Integer>(toIdentifier.get(test)));
        }

        Map<Integer, T> newValues = new HashMap<Integer, T>(values);
        
        Map<Integer, Map<K, Set<Condition>>> newRules = new HashMap<Integer, Map<K, Set<Condition>>>(toCondition);
        
        return new RuleMap<K, T>(newValues, newRules, newIdentifiers);
    }
}

package com.goodworkalan.winnow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// TODO Document.
public class RuleMap<T> {
    /** The map of identifiers to object values. */
    private final Map<Integer, T> values;
    
    /** The map of identifiers to the conditions that match them. */
    private final Map<Integer, Map<Object, Set<Condition>>> toCondition;
    
    /** The map of conditions to the identifiers they match. */
    private final Map<Map<Object, Condition>, Set<Integer>> toIdentifier;

    /**
     * Create a new rule map.
     * 
     * @param values
     *            The map of identifiers to object values.
     * @param toCondition
     *            The map of identifiers to the conditions that match them.
     * @param toIdentifier
     *            The map of conditions to the identifiers they match.
     */
    RuleMap(Map<Integer, T> values, Map<Integer, Map<Object, Set<Condition>>> toCondition, Map<Map<Object, Condition>, Set<Integer>> toIdentifier) {
        this.values = values;
        this.toCondition = toCondition;
        this.toIdentifier = toIdentifier;
    }

    /**
     * Return a list of objects that match the given set of conditions in the
     * order in which they were specified by the rule set builder.
     * 
     * @param map
     *            The map of conditions.
     * @return The objects that match the conditions in the order in which they
     *         were mapped.
     */
    public List<T> get(Map<Object, Object> map) {
        // Create a copy for elimination.
        Map<Integer, Map<Object, Set<Condition>>> tests = new TreeMap<Integer, Map<Object,Set<Condition>>>();
        for (Map.Entry<Integer, Map<Object, Set<Condition>>> test : toCondition.entrySet()) {
            Map<Object, Set<Condition>> newConditions = new HashMap<Object, Set<Condition>>();
            for (Map.Entry<Object, Set<Condition>> conditions: test.getValue().entrySet()) {
                newConditions.put(conditions.getKey(), new HashSet<Condition>(conditions.getValue()));
            }
            tests.put(test.getKey(), newConditions);
        }

        Map<Map<Object, Condition>, Set<Integer>> identifiers = new LinkedHashMap<Map<Object,Condition>, Set<Integer>>();
        for (Map.Entry<Map<Object, Condition>, Set<Integer>> entry : toIdentifier.entrySet()) {
            identifiers.put(entry.getKey(), new HashSet<Integer>(entry.getValue()));
        }
        
        // Pull the first condition from the remaining conditions, while there 
        // are still conditions.
        while (identifiers.size() != 0) {
            Map.Entry<Map<Object, Condition>, Set<Integer>> entry = identifiers.entrySet().iterator().next();

            identifiers.remove(entry.getKey());
         
            // Get the object key and condition. 
            Map.Entry<Object, Condition> test = entry.getKey().entrySet().iterator().next();
            
            if (test.getValue().test(map.get(test.getKey()))) {
                for (int id : entry.getValue()) {
                    Map<Object, Set<Condition>> expression = tests.get(id);
                    Iterator<Condition> or = expression.get(test.getKey()).iterator();
                    while (or.hasNext()) {
                        Condition condition = or.next();
                        if (!condition.equals(test.getValue())) {
                            Map<Object, Condition> c = Collections.singletonMap(test.getKey(), condition);
                            Set<Integer> usage = identifiers.get(c);
                            usage.remove(id);
                            if (usage.size() == 0) {
                                identifiers.remove(c);
                            }
                            or.remove();
                        }
                    }
                }
            } else {
                for (int id : entry.getValue()) {
                    Map<Object, Set<Condition>> expression = tests.get(id);
                    Set<Condition> or = expression.get(test.getKey());
                    or.remove(test.getValue());
                    if (or.size() == 0) {
                        tests.remove(id);
                        expression.remove(test.getKey());
                        for (Map.Entry<Object, Set<Condition>> foo : expression.entrySet()) {
                            for (Condition bar : foo.getValue()) {
                                Map<Object, Condition> c = Collections.singletonMap(foo.getKey(), bar);
                                Set<Integer> usage = identifiers.get(c);
                                if (usage != null) {
                                    usage.remove(id);
                                    if (usage.size() == 0) {
                                        identifiers.remove(c);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        List<T> objects = new ArrayList<T>();
        for (int id : tests.keySet()) {
            objects.add(values.get(id));
        }

        return objects;
    }
}

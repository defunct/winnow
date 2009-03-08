package com.goodworkalan.deviate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO Document.
public class KeyedParameters<T>
{
    // TODO Document.
    private final RuleMap<T> conditions;
    
    // TODO Document.
    private final Map<Object, Object> map;
    
    // TODO Document.
    public KeyedParameters(RuleMap<T> conditions)
    {
        this.conditions = conditions;
        this.map = new HashMap<Object, Object>();
    }
    
    // TODO Document.
    public KeyedParameters<T> put(Object key, Object value)
    {
        map.put(key, value);
        return this;
    }
    
    // TODO Document.
    public List<T> get()
    {
        return conditions.get(map);
    }
}

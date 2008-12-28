package com.goodworkalan.diverge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyedParameters<T>
{
    private final RuleMap<T> conditions;
    
    private final Map<Object, Object> map;
    
    public KeyedParameters(RuleMap<T> conditions)
    {
        this.conditions = conditions;
        this.map = new HashMap<Object, Object>();
    }
    
    public KeyedParameters<T> put(Object key, Object value)
    {
        map.put(key, value);
        return this;
    }
    
    public List<T> get()
    {
        return conditions.get(map);
    }
}

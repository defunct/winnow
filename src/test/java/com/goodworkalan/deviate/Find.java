package com.goodworkalan.deviate;

import java.util.regex.Pattern;

import com.goodworkalan.deviate.Condition;

public class Find implements Condition
{
    private final Pattern pattern;
    
    public Find(String regex)
    {
        this.pattern = Pattern.compile(regex);
    }
    
    public boolean test(Object object)
    {
        return pattern.matcher((String) object).find();
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Find)
        {
            Find find = (Find) object;
            return pattern.pattern().equals(find.pattern.pattern())
                && pattern.flags() == find.pattern.flags();
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hashCode = 7;
        hashCode = hashCode * 37 + pattern.pattern().hashCode();
        hashCode = hashCode * 37 + pattern.flags();
        return hashCode;
    }
    
    public String toString()
    {
        return "Find(" + pattern.pattern() + ")";
    }
}

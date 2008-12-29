package com.goodworkalan.diverge;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.goodworkalan.diverge.Any;
import com.goodworkalan.diverge.Equals;
import com.goodworkalan.diverge.RuleMapBuilder;

public class RuleMapTest
{
    @Test
    public void create()
    {
        new RuleMapBuilder<String>().newRuleMap();
    }
    
    @Test
    public void put()
    {
        RuleMapBuilder<String> conditions = new RuleMapBuilder<String>();
        conditions.rule()
                  .check(one, new Find("a")).or(new Find("b")).put("X");
        List<String> strings = conditions.newRuleMap().test().put(one, "a").get();
        assertEquals(strings.size(), 1);
    }

    Object one = "ONE";
    Object two = "TWO";
    
    @Test
    public void any()
    {
        assertTrue(new Any().test(null));
        assertTrue(new Any().equals(new Any()));
        assertFalse(new Any().equals("Fred"));
        assertEquals(new Any().hashCode(), 17);
    }
    
    @Test
    public void equals()
    {
        Equals equals = new Equals("X");
        assertTrue(equals.equals(equals));
        assertFalse(new Equals("X").equals("X"));
        assertTrue(new Equals("X").equals(new Equals("X")));
        assertFalse(new Equals("X").equals(new Equals("Y")));
        assertTrue(new Equals(null).equals(new Equals(null)));
        assertFalse(new Equals("X").equals(new Equals(null)));
        assertFalse(new Equals(null).equals(new Equals("X")));
        assertEquals(new Equals("X").hashCode(), "X".hashCode());
        assertEquals(new Equals(null).hashCode(), 17);
        assertTrue(new Equals("X").test("X"));
        assertFalse(new Equals("X").test("Y"));
        assertTrue(new Equals(null).test(null));
        assertFalse(new Equals("X").test(null));
        assertFalse(new Equals(null).test("X"));
    }
    
    @Test
    public void get()
    {
        RuleMapBuilder<String> newRules = new RuleMapBuilder<String>();
        newRules.rule().check(one, new Any()).put("X");
    }
    
    @Test
    public void notFound()
    {
        RuleMapBuilder<String> newRules = new RuleMapBuilder<String>();
        newRules.rule()
                .check(one, new Equals("A")).or(new Equals("B")).put("W");
        newRules.rule()
                .check(one, new Equals("A")).or(new Equals("C")).put("X");
        newRules.rule()
                .check(one, new Equals("A")).or(new Equals("C")).put("Y");
        newRules.rule()
                .check(one, new Equals("A")).or(new Equals("C")).or(new Equals("X"))
                .check(two, new Equals("C")).put("Z");
        newRules.rule()
                .check(one, new Equals("A"))
                .check(two, new Equals("C")).put("M");
        newRules.rule()
                .check(one, new Equals("B"))
                .check(two, new Equals("C")).put("N");
        newRules.rule()
                .check(one, new Equals("Z"))
                .check(two, new Equals("C")).put("O");
        newRules.rule()
                .check(one, new Equals("D"))
                .check(two, new Equals("C")).put("P");
        RuleMap<String> rules = newRules.newRuleMap();
        List<String> strings = rules.test().put(one, "A").put(two, "B").get();
        assertEquals(strings.size(), 3);
        strings = rules.test().put(one, "B").put(two, "B").get();
        assertEquals(strings.size(), 1);
        strings = rules.test().put(one, "X").put(two, "Y").get();
        assertEquals(strings.size(), 0);
    }
}

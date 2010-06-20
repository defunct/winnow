package com.goodworkalan.winnow;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

public class RuleMapTest
{
    @Test
    public void create()
    {
        new RuleMapBuilder<String>().newRuleMap();
    }
    
    @Test
	public void put() {
        RuleMapBuilder<String> rules = new RuleMapBuilder<String>();
        rules.rule()
                  .check(one, new Find("a")).check(one, new Find("b")).put("X");
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(one, "a");
        List<String> strings = rules.newRuleMap().get(conditions);
        assertEquals(strings.size(), 1);
    }

    Object one = "ONE";
    Object two = "TWO";
    
	@Test
	public void equals() {
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
	public void get() {
        RuleMapBuilder<String> newRules = new RuleMapBuilder<String>();
        newRules.rule().check(one, new Condition() {
			public boolean test(Object object) {
				return true;
			}
		}).put("X");
    }
    
    @Test
	public void notFound() {
        RuleMapBuilder<String> newRules = new RuleMapBuilder<String>();
        newRules.rule()
                .check(one, new Equals("A")).check(one, new Equals("B")).put("W");
        newRules.rule()
                .check(one, new Equals("A")).check(one, new Equals("C")).put("X");
        newRules.rule()
                .check(one, new Equals("A")).check(one, new Equals("C")).put("Y");
        newRules.rule()
                .check(one, new Equals("A")).check(one, new Equals("C")).check(one, new Equals("X"))
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
        Map<Object, Object> conditions = new HashMap<Object, Object>();
        conditions.put(one, "A");
        conditions.put(two, "B");
        List<String> strings = rules.get(conditions);
        assertEquals(strings.size(), 3);
        conditions.clear();
        conditions.put(one, "B");
        conditions.put(two, "B");
        strings = rules.get(conditions);
        assertEquals(strings.size(), 1);
        conditions.clear();
        conditions.put(one, "X");
        conditions.put(two, "Y");
        strings = rules.get(conditions);
        assertEquals(strings.size(), 0);
    }
    
	@Test(expectedExceptions = NullPointerException.class)
	public void npePutExpression() {
		new RuleMapBuilder<String>().put(null, "X");
	}
}

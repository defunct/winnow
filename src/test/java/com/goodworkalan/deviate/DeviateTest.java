package com.goodworkalan.deviate;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

public class DeviateTest
{
    private static <T> void put(Conditions<T> deviations, T value, Match...matches)
    {
        List<Set<Match>> listOfMatches = new ArrayList<Set<Match>>();
        for (Match match : matches)
        {
            listOfMatches.add(Collections.singleton(match));
        }
        deviations.put(listOfMatches, value);
    }

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void toSmall()
    {
        new Conditions<String>(0);
    }

    @Test
    public void create()
    {
        new Conditions<String>(3);
    }
    
    @Test
    public void put()
    {
        Conditions<String> deviations = new Conditions<String>(3);
        put(deviations, "X", new Any(), new Any(), new Any());
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void badPut()
    {
        Conditions<String> deviations = new Conditions<String>(3);
        put(deviations, "X", new Any(), new Any());
    }
    
    @Test
    public void any()
    {
        assertTrue(new Any().match(null));
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
        assertTrue(new Equals("X").match("X"));
        assertFalse(new Equals("X").match("Y"));
        assertTrue(new Equals(null).match(null));
        assertFalse(new Equals("X").match(null));
        assertFalse(new Equals(null).match("X"));
    }
    
    @Test
    public void get()
    {
        Conditions<String> deviations = new Conditions<String>(3);
        put(deviations, "X", new Any(), new Any(), new Any());
        List<String> strings = deviations.get(null, null, null);
        assertEquals(strings.size(), 1);
        assertEquals(strings.get(0), "X");
    }
    
    @Test
    public void notFound()
    {
        Conditions<String> deviations = new Conditions<String>(3);
        put(deviations, "X", new Equals("A"), new Equals("B"), new Any());
        put(deviations, "X", new Equals("A"), new Equals("C"), new Any());
        put(deviations, "Y", new Equals("A"), new Equals("C"), new Any());
        List<String> strings = deviations.get(null, null, null);
        assertEquals(strings.size(), 0);
        strings = deviations.get("A", "A", "A");
        assertEquals(strings.size(), 0);
        strings = deviations.get("A", "B", "C");
        assertEquals(strings.size(), 1);
        assertEquals(strings.get(0), "X");
        strings = deviations.get("A", "C", "C");
        assertEquals(strings.size(), 2);
        assertEquals(strings.get(0), "X");
        assertEquals(strings.get(1), "Y");
    }
}

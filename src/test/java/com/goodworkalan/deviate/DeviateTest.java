package com.goodworkalan.deviate;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

public class DeviateTest
{
    private static <T> void put(Deviations<T> deviations, T value, Match...matches)
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
        new Deviations<String>(0);
    }

    @Test
    public void create()
    {
        new Deviations<String>(3);
    }
    
    @Test
    public void add()
    {
        Deviations<String> deviations = new Deviations<String>(3);
        put(deviations, "X", new Any(), new Any(), new Any());
    }
    
    @Test
    public void any()
    {
        assertTrue(new Any().match(null));
    }
    
    @Test
    public void get()
    {
        Deviations<String> deviations = new Deviations<String>(3);
        put(deviations, "X", new Any(), new Any(), new Any());
        List<String> strings = deviations.get(null, null, null);
        assertEquals(strings.size(), 1);
        assertEquals(strings.get(0), "X");
    }
}

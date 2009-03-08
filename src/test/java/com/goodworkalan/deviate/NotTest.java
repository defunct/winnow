package com.goodworkalan.deviate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.goodworkalan.deviate.Equals;
import com.goodworkalan.deviate.Not;

public class NotTest
{
    @Test
    public void notHashCode()
    {
        new Not(new Equals("a")).hashCode();
    }
    
    @Test
    public void notEquals()
    {
        Not not = new Not(new Equals("a"));
        assertEquals(not, not);
        assertFalse(not.equals("x"));
        assertEquals(not, new Not(new Equals("a")));
    }

    @Test
    public void not()
    {
        Not not = new Not(new Find("a"));
        assertFalse(not.test("ab"));
        assertTrue(not.test("bc"));
    }
}

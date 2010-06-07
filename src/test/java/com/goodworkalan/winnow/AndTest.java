package com.goodworkalan.winnow;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.goodworkalan.winnow.And;
import com.goodworkalan.winnow.Equals;

public class AndTest
{
    @Test
    public void andHashCode()
    {
        new And(new Equals("a"), new Equals("b")).hashCode();
    }
    
    @Test
    public void andEquals()
    {
        And and =        new And(new Equals("a"), new Equals("b"));
        assertEquals(and, and);
        assertFalse(and.equals("x"));
        assertEquals(and, new And(new Equals("a"), new Equals("b")));
    }

    @Test
    public void and()
    {
        And and = new And(new Find("a"), new Find("b"));
        assertFalse(and.test("a"));
        assertTrue(and.test("ab"));
    }
}

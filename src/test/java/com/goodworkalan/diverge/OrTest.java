package com.goodworkalan.diverge;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class OrTest
{
    @Test
    public void orHashCode()
    {
        new Or(new Equals("a"), new Equals("b")).hashCode();
    }
    
    @Test
    public void orEquals()
    {
        Or or = new Or(new Equals("a"), new Equals("b"));
        assertEquals(or, or);
        assertFalse(or.equals("x"));
        assertEquals(or, new Or(new Equals("a"), new Equals("b")));
    }

    @Test
    public void or()
    {
        Or or = new Or(new Find("a"), new Find("b"));
        assertTrue(or.test("a"));
        assertTrue(or.test("b"));
        assertFalse(or.test("c"));
    }
}

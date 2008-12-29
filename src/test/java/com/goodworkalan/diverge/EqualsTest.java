package com.goodworkalan.diverge;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class EqualsTest
{
    @Test
    public void string()
    {
        assertEquals(new Equals("X").toString(), "Equals(X)");
    }
}

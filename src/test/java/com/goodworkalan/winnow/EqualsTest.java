package com.goodworkalan.winnow;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.goodworkalan.winnow.Equals;

public class EqualsTest {
    @Test
    public void string() {
        assertEquals(new Equals("X").toString(), "Equals(X)");
    }
}

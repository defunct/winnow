package com.goodworkalan.winnow;

/**
 * A condition to meet.
 * <p>
 * Conditions are mapped to keys, so that during a test, a map of keys to
 * objects are provided, and the object given to the codition is the object
 * mapped to the same key as the condition.
 * 
 * @author Alan Gutierrez
 */
public interface Condition {
    /**
     * Test that the given <code>object</code> meets this condition.
     * 
     * @param object
     *            The object to test.
     * @return <code>true</code> if the object meets the condition,
     *         <code>false</code> otherwise.
     */
    public boolean test(Object object);
}

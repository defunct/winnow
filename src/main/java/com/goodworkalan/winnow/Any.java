package com.goodworkalan.winnow;

/**
 * A test that always returns true.
 * 
 * @author Alan Gutierrez
 */
public class Any implements Condition
{
    /**
     * Always return true.
     * 
     * @param object
     *            The object to test.
     * @returns True, always.
     */
    public boolean test(Object object)
    {
        return true;
    }
    
    /**
     * This any test is equal to the given object if the given object is also
     * an any test.
     * 
     * @param object
     *            An object to compare to this object.
     * @return True if this object is equal to the given object.
     */
    @Override
    public boolean equals(Object object)
    {
        return (object instanceof Any);
    }
    
    /**
     * Return the same hash code for all instances.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode()
    {
        return 17;
    }
}

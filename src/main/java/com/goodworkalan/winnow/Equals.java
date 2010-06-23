package com.goodworkalan.winnow;

/**
 * Tests the equality of a object against a criteria object.
 *
 * @author Alan Gutierrez
 */
public class Equals implements Condition {
    /** The expected object to test for equality. */
    private final Object expected;
    
    /**
     * Create an equality test with the given expected object to test for
     * equality.
     * 
     * @param expected
     *            The expected object to test for equality.
     */
    public Equals(Object expected) {
        this.expected = expected;
    }

    /**
     * Return whether the given object is equal to the expected object.
     * 
     * @return True if the given object is equal to the expected object.
     */
    public boolean test(Object object) {
        if (expected == null) {
            return object == null;
        }
        return expected.equals(object);
    }

    /**
     * This object is equal to the given object if the given object is also an
     * instance of <code>Equals</code> and the expected objects are equal.
     * 
     * @param object
     *            The object to test for equality.
     * @return True if this object equals the given object.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Equals) {
            Equals equals = (Equals) object;
            return test(equals.expected);
        }
        return false;
    }

    /**
     * Generate a hash code from the hash code of the expected object.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return expected == null ? getClass().hashCode() : expected.hashCode();
    }

    /**
     * Generate a string representation of this equality condition.
     * 
     * @return A string representation of this object.
     */
    public String toString() {
        return "Equals(" + expected + ")";
    }
}

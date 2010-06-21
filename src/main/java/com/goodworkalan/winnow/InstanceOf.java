package com.goodworkalan.winnow;

/**
 * Tests to see if a property is an instance of a specific class.
 * 
 * @author Alan Gutierrez
 */
public class InstanceOf implements Condition {
    /** Test to see if the property is an instance of this class. */
    private final Class<?> targetClass;

    /**
     * Create an instance of test that will test to see if the property is an
     * instance of the given class.
     * 
     * @param targetClass
     *            Test to see if the proeprty is an instance of this class.
     */
    public InstanceOf(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    /**
     * Test to see if the given object is not null and an instance of the target
     * class property.
     * 
     * @param object
     *            The object to test.
     * @return True if the object is not null and is an instance of the target
     *         class property.
     */
    public boolean test(Object object) {
        return object != null
                && targetClass.isAssignableFrom(object.getClass());
    }

    /**
     * This instance of test is equal to the given object if the given object is
     * an instance of test with the same target class.
     * 
     * @param object
     *            An object to compare to this object.
     * @return True if this object is equal to the given object.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof InstanceOf) {
            InstanceOf instanceOf = (InstanceOf) object;
            return targetClass.equals(instanceOf.targetClass);
        }
        return false;
    }
    
    /**
     * Create a hash code using the hash code of the target class property.
     * 
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return targetClass.hashCode();
    }

    /**
     * Create a string representation of this instance of test.
     * 
     * @return A string representation.
     */
    @Override
    public String toString() {
        return "InstanceOf(" + targetClass.getCanonicalName() + ")";
    }
}

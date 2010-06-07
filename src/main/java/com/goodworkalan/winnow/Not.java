package com.goodworkalan.winnow;

/**
 * Negates a condition returning true if the contained condition is false.
 * 
 * @author Alan Gutierrez
 */
public class Not implements Condition {
    /** The condition to negate. */
    private final Condition condition;

    /**
     * Return true if the given condition evaluates to false.
     * 
     * @param condition
     *            The condition to negate.
     */
    public Not(Condition condition) {
        this.condition = condition;
    }

    /**
     * Return true if evaluating the evaluation of the contained condition with
     * the given object returns false.
     * 
     * @return The negated result of the contained condition evaluation.
     */
    public boolean test(Object object) {
        return !condition.test(object);
    }

    /**
     * This object is equal to the given object if the given object is also an
     * instance of <code>Not</code> and the contained conditions are equal.
     * 
     * @return True if this object is equalt to the given object.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Not) {
            Not not = (Not) object;
            return condition.equals(not.condition);
        }
        return false;
    }
    
    /**
     * Creates a hash code by returning the hash code of the contained
     * condition.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return condition.hashCode();
    }
}

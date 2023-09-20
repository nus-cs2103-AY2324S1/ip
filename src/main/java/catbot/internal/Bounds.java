package catbot.internal;

/**
 * Represents a range of integers by keeping track of their lower and upper bounds.
 */
public class Bounds {
    private final int lowerBound;
    private final int upperBound;

    /**
     * Constructs a bound using the provided lower and upper bounds, inclusive.
     * If lowerBound is greater than upper bound, the bound does not contain any integer.
     *
     * @param lowerBound lower bound of the integer range.
     * @param upperBound upper bound of the inteer range.
     */
    public Bounds(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Checks if the provided integer is within the bounds.
     *
     * @param i integer to check.
     * @return true if between lower and upper bounds, inclusive.
     */
    public boolean contains(int i) {
        return this.lowerBound <= i && i <= this.upperBound;
    }

    /**
     * Retrieve the inclusive lower bound of the Bound.
     *
     * @return lower bound, inclusive.
     */
    public int getLower() {
        return lowerBound;
    }

    /**
     * Retrieve the inclusive upper bound of the Bound.
     *
     * @return upper bound, inclusive.
     */
    public int getUpper() {
        return upperBound;
    }
}

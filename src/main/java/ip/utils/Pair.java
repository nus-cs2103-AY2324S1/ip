package ip.utils;

/**
 * Helper Data Structure for the iP. <br>
 * A custom implementation of a paired data structure, as Java lacks a ready solution
 * that involves an ordered pair. Simplified, as we do not need a complicated solution
 * as of now.
 *
 * @param <S> The Type of the first value in the Pair.
 * @param <T> The Type of the second value in the Pair.
 *
 */
public class Pair<S, T> {
    /** The first value to store in the pair. **/
    private S first;

    /** The second value to store in the pair. **/
    private T second;

    /**
     * Constructor for the Pair class.
     * @param first The value to store in the first slot of the pair, of type S.
     * @param second The value to store in the second slot of the pair, of type T.
     */
    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return The first value in the pair.
     */
    public S getFirst() {
        return this.first;
    }

    /**
     * @return The first value in the pair.
     */
    public T getSecond() {
        return this.second;
    }
}

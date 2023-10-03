package duke.util;

/**
 * Represents an immutable key-value pair.
 */
public class Pair {

    private final String key;
    private final String value;

    /**
     * Initializes a new Pair with the specified key and value.
     *
     * @param key   The key for this pair.
     * @param value The value to be paired with the specified key.
     */
    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Retrieves the key of this pair.
     *
     * @return The key associated with this pair.
     */
    public String getKey() {
        return key;
    }

    /**
     * Retrieves the value of this pair.
     *
     * @return The value associated with this pair.
     */
    public String getValue() {
        return value;
    }
}

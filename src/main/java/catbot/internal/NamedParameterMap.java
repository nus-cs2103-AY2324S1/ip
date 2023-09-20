package catbot.internal;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Maps Strings to Strings with limited functionality,
 * suitable to be used as small containers for parameter-argument pairs.
 */
public class NamedParameterMap {

    private final HashMap<String, String> parameters;

    /**
     * Constructor for a NamedParameterMap.
     */
    public NamedParameterMap() {
        parameters = new HashMap<>();
    }

    /**
     * Checks if the map has a value mapped to the given key.
     *
     * @param key the key for which to check if a corresponding value exists.
     * @return true if a value exists for the key, false otherwise.
     */
    public boolean containsKey(String key) {
        return parameters.containsKey(key);
    }

    /**
     * Changes the key of a value to a new key, if the corresponding value exists.
     *
     * @param oldKey key to identify the value to change the key of.
     * @param newKey new key to associate the value to.
     */
    public void moveToNewKey(String oldKey, String newKey) {
        if (!parameters.containsKey(newKey) && parameters.containsKey(oldKey)) {
            parameters.put(newKey, parameters.get(oldKey));
            parameters.remove(oldKey);
        }
    }

    /**
     * Removes a key-value pair from the map.
     *
     * @param key key of the key-value pair to remove.
     * @return value of the removed key-value pair.
     */
    public String remove(String key) {
        if (!containsKey(key)) {
            return null;
        }

        return parameters.remove(key);
    }

    /**
     * Retrieves a Set of all keys.
     *
     * @return set of all keys.
     */
    public Set<String> keySet() {
        return parameters.keySet();
    }

    /**
     * Retrieves the value corresponding to the given key.
     *
     * @param key key of the key-value pair to find.
     * @return value of the key-value pair found, null otherwise.
     */
    public String get(String key) {
        return parameters.get(key);
    }

    /**
     * @param parameterName  key with which the specified value is to be associated
     * @param parameterValue value to be associated with the specified key
     * @return this, for pipelining
     */
    public NamedParameterMap addNamedParameter(String parameterName, String parameterValue) {
        parameters.put(parameterName, parameterValue);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        // full credit to IntelliJ, which is smarter than I am
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NamedParameterMap that = (NamedParameterMap) o;
        return Objects.equals(parameters, that.parameters);
    }

    @Override
    public int hashCode() {
        // full credit to IntelliJ, which is smarter than I am
        return Objects.hash(parameters);
    }
}

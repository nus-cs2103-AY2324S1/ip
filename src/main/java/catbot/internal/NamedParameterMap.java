package catbot.internal;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Maps Strings to Strings with limited functionality, suitable to be used as small containers for parameter-argument
 * pairs, but which transitions to a HashMap at scale. todo
 */
public class NamedParameterMap {

    private final HashMap<String, String> parameters;

    public NamedParameterMap() {
        parameters = new HashMap<>();
    }

    public boolean containsKey(String key) {
        return parameters.containsKey(key);
    }

    public void moveToNewKey(String oldKey, String newKey) {
        if (!parameters.containsKey(newKey) && parameters.containsKey(oldKey)) {
            parameters.put(newKey, parameters.get(oldKey));
            parameters.remove(oldKey);
        }
    }

    public Set<String> keySet() {
        return parameters.keySet();
    }

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

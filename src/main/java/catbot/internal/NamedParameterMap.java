package catbot.internal;

import java.util.HashMap;
import java.util.Set;

public class NamedParameterMap {

    private HashMap<String, String> parameters;

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

    public NamedParameterMap addNamedParameter(String parameterName, String parameterValue) {
        parameters.put(parameterName, parameterValue);
        return this;
    }
}

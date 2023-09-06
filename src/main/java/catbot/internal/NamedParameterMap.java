package catbot.internal;

import java.util.HashMap;

public class NamedParameterMap extends HashMap<String, String> {

    public NamedParameterMap addNamedParameter(String parameterName, String parameterValue) {
        this.put(parameterName, parameterName);
        return this;
    }
}

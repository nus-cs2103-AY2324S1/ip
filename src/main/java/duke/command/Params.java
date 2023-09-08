package duke.command;

import duke.error.DukeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a set of parameters associated with a command. Parameters are stored as key-value pairs.
 */
public class Params {
    public static final String ARGUMENT_KEY = "arg";
    /**
     * Map containing parameter names as keys and their corresponding values as values.
     */
    private Map<String, String> paramMap;

    /**
     * Constructs a Params object with the provided parameter map.
     *
     * @param paramMap The map of parameters.
     */
    public Params(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * Constructs a Params object with an empty parameter map.
     */
    public Params() {
        this.paramMap = new HashMap<>();
    }

    /**
     * Retrieves the value associated with a specified parameter name.
     *
     * @param paramName The name of the parameter.
     * @return The value of the parameter, or null if the parameter is not found.
     */
    public String getParam(String paramName) {
        return paramMap.get(paramName);
    }

    public String getArgument() {
        return paramMap.get(ARGUMENT_KEY);
    }

    /**
     * Retrieves the value associated with a specified parameter name.
     *
     * @param paramName The name of the parameter.
     * @return The value of the parameter, or null if the parameter is not found.
     */
    public String getParamIfSet(String paramName, String usageText) throws DukeException {
        String param = getParam(paramName);
        if (param == null || param.equals("")) {
            throw new DukeException(String.format("%s cannot be empty\n\n\tUsage: %s", paramName, usageText));
        }
        return param;
    }

    public String getArgumentIfSet(String argumentLabel, String usageText) throws DukeException {
        String param = getArgument();
        if (param == null || param.equals("")) {
            throw new DukeException(String.format(
                "%s cannot be empty\n\n\tUsage: %s", argumentLabel, usageText));
        }
        return param;
    }

    /**
     * Sets a parameter with the specified name and value.
     *
     * @param paramName The name of the parameter.
     * @param value     The value of the parameter.
     */
    public void setParam(String paramName, String value) throws DukeException {
        if (paramName.equals(ARGUMENT_KEY)) {
            throw new DukeException(String.format("%s is a reserved argument name!", ARGUMENT_KEY));
        }
        paramMap.put(paramName, value);
    }

    /**
     * Sets a parameter with the specified name and value.
     *
     * @param value The value of the parameter.
     */
    public void setArgument(String value) {
        paramMap.put(ARGUMENT_KEY, value);
    }

    /**
     * Returns a string representation of the parameters.
     *
     * @return A string representing the parameters in the format "paramName1: paramValue1, paramName2: paramValue2,
     * ..."
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder.toString();
    }
}

package duke.Utils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import java.lang.NumberFormatException;

import java.time.LocalDateTime;

/**
 * The Command class provides utility methods for parsing and validating
 * command arguments in a specific format.
 */
public class Command {
    // Regular expression pattern for date and time in the format "YYYY-MM-DD HH:mm"
    private static final Pattern DATE_RX = Pattern.compile(
        "^"
        + "("
        + "((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)" 
        + "|(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))"
        + "|(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))" 
        + "|(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))"
        + ")"
        + "\\s"
        + "([01]?[0-9]|2[0-3]):[0-5][0-9]"
        + "$"
      ); // YYYY-MM-DD HH:mm
    
    /**
     * Enumeration representing the types of command arguments.
     */
    enum Type {
        NONE,
        INTEGER,
        STRING,
        DATETIME
    }

    /**
     * Extracts the value of a specific argument from an input string.
     *
     * @param input      The input string containing arguments.
     * @param targetArg  The name of the argument to extract.
     * @return           The value of the specified argument.
     */
    private static String getArg(String input, String targetArg) {
        String[] args = input.split("/");
        for (String arg : args) {
            String[] words = arg.split(" ");
            String argName = words[0];
            if (argName.equals(targetArg)) {
                return Arrays.stream(words).skip(1).collect(Collectors.joining(" "));
            }
        }
        return "";
    }

    /**
     * Validates and retrieves a string argument from the input.
     *
     * @param input     The input string containing arguments.
     * @param argName   The name of the string argument to validate and retrieve.
     * @return          The validated string argument value.
     * @throws InvalidArgumentException if the argument is missing or empty.
     */
    protected static String assertString(String input, String argName) throws InvalidArgumentException {
        String arg = Command.getArg(input, argName);
        if (arg.isEmpty()) {
            throw new InvalidArgumentException(argName, Type.STRING);
        }
        return arg;
    }

    /**
     * Validates and retrieves an integer argument from the input.
     *
     * @param input     The input string containing arguments.
     * @param argName   The name of the integer argument to validate and retrieve.
     * @return          The validated integer argument value.
     * @throws InvalidArgumentException if the argument is missing, empty, or not a valid integer.
     */
    protected static Integer assertInteger(String input, String argName) throws InvalidArgumentException {
        try {
            String arg = Command.getArg(input, argName);
            if (arg.isEmpty()) {
                throw new InvalidArgumentException(argName, Type.INTEGER);
            }
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(argName, Type.INTEGER);
        }
    }

    /**
     * Validates and retrieves a LocalDateTime argument from the input.
     *
     * @param input     The input string containing arguments.
     * @param argName   The name of the DateTime argument to validate and retrieve.
     * @return          The validated LocalDateTime argument value.
     * @throws InvalidArgumentException if the argument is missing or not in the expected format.
     */
    protected static LocalDateTime assertDateTime(String input, String argName) throws InvalidArgumentException {
        String arg = Command.getArg(input, argName);
        System.out.println(arg + '|');
        if (!Command.DATE_RX.matcher(arg).matches()) {
            throw new InvalidArgumentException(argName, Type.DATETIME);
        }
        String[] timeSplit = arg.split(" ");
        CharSequence timeSequence = 
            timeSplit[0]
            + "T"
            + timeSplit[1]
            + ":00";
        return LocalDateTime.parse(timeSequence);
    }
}
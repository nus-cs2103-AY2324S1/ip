package duke.Utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.regex.Pattern;

import java.time.LocalDateTime;

/**
 * The FileIO class provides utility methods for reading and writing data to/from files
 * in the context of the Duke application.
 */
public class FileIO {
    private static final String DELIMITER = "-|-";
    private static final String SPLIT_DELIMITER = "(-\\|-)";
    private static final Pattern DATE_REGEX = Pattern.compile(
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
     * Joins an array of objects into a single string, using the specified delimiter.
     *
     * @param str The objects to be joined.
     * @return The joined string.
     */
    protected static String joinCsv(Object... str) {
        return Stream.of(str)
            .map(Object::toString)
            .collect(Collectors.joining(FileIO.DELIMITER));
    }
    
    /**
     * Reads a list of CSV lines and converts them into a list of Task objects.
     *
     * @param lines The list of CSV lines to be parsed.
     * @return A list of Task objects parsed from the CSV lines.
     * @throws InvalidFileDataException if the data in the file is invalid or cannot be parsed.
     */
    protected static ArrayList<Task> readCsv(ArrayList<String> lines) throws InvalidFileDataException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] args = line.split(FileIO.SPLIT_DELIMITER);
            Task.Type type = Task.Type.of(args[0]);
            assertParams(args, type);
            switch(type) {
            case TODO:
                tasks.add(Todo.of(args));
                break;
            case DEADLINE:
                tasks.add(Deadline.of(args));
                break;
            case EVENT:
                tasks.add(Event.of(args));
                break;
            default:
                throw new InvalidFileDataException();
            }
        }
        return tasks;
    }
    
    /**
     * Asserts that the number of parameters in an argument array matches the expected number
     * based on the Task type.
     *
     * @param args The argument array to be checked.
     * @param type The Task type used to determine the expected number of parameters.
     * @throws InvalidFileDataException if the number of parameters does not match the expected count.
     */
    protected static void assertParams(String[] args, Task.Type type) throws InvalidFileDataException {
        if (args.length != type.param()) {
            throw new InvalidFileDataException();
        }
    }

    /**
     * Asserts that a string is not empty.
     *
     * @param input The input string to be checked.
     * @return The input string if it is not empty.
     * @throws InvalidFileDataException if the input string is empty.
     */
    protected static String assertString(String input) throws InvalidFileDataException {
        if (input.isEmpty()) {
            throw new InvalidFileDataException();
        }
        return input;
    }

    /**
     * Asserts that a string represents a boolean value ("true" or "false").
     *
     * @param input The input string to be checked.
     * @return The boolean value represented by the input string.
     * @throws InvalidFileDataException if the input string does not represent a valid boolean.
     */
    protected static boolean assertBoolean(String input) throws InvalidFileDataException {
        if (input.equals("true")) {
            return true;
        } else if (input.equals("false")) {
            return false;
        }
        throw new InvalidFileDataException();
    }

    /**
     * Asserts that a string can be parsed into an integer.
     *
     * @param input The input string to be checked.
     * @return The integer value parsed from the input string.
     * @throws InvalidFileDataException if the input string cannot be parsed as an integer.
     */
    protected static Integer assertInteger(String input) throws InvalidFileDataException {
        try {
            if (input.isEmpty()) {
                throw new InvalidFileDataException();
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidFileDataException();
        }
    }

    /**
     * Asserts that a string matches a specific date and time format, particularly [YYYY-MM-DD HH:mm].
     *
     * @param input The input string to be checked.
     * @return The LocalDateTime object representing the parsed date and time.
     * @throws InvalidFileDataException if the input string does not match the expected date and time format.
     */
    protected static LocalDateTime assertDateTime(String input) throws InvalidFileDataException {
        if (!FileIO.DATE_REGEX.matcher(input).matches()) {
            throw new InvalidFileDataException();
        }
        String[] timeSplit = input.split(" ");
        CharSequence timeSequence = 
            timeSplit[0]
            + "T"
            + timeSplit[1]
            + ":00";
        return LocalDateTime.parse(timeSequence);
    }
}
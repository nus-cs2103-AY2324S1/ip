package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

/**
 * Helper class to parse user input.
 */
public class Parser {
    private static final String DELIMITER_REGEX = " (?=/\\w+)";

    private final String command;
    private final String argument;

    private final HashMap<String, String> optionalArgs;

    private Parser(String command) {
        this.command = command;
        this.argument = null;
        this.optionalArgs = null;
    }

    private Parser(String command, String argument, HashMap<String, String> optionalArgs) {
        this.command = command;
        this.argument = argument;
        this.optionalArgs = optionalArgs;
    }

    /**
     * Factory method to parses line of input and
     * extract task type, name and optional args.
     * @param line User input.
     * @return duke.Parser instance with parsed information.
     * @throws DukeException If input does not match expected format.
     */
    public static Parser from(String line) throws DukeException {
        String taskName;
        String taskType;
        HashMap<String, String> optionalArgs;

        int firstSpace = line.indexOf(' ');

        // No arguments (bye/list/...)
        if (firstSpace == -1) {
            return new Parser(line);
        }

        taskType = line.substring(0, firstSpace);
        String remainder = line.substring(firstSpace + 1);
        String[] parts = remainder.split(DELIMITER_REGEX);

        if (parts.length < 1 || parts[0].equals("")) {
            throw new DukeException("Invalid command. Expected format: <task type> <task name> [...].");
        }

        taskName = parts[0];
        optionalArgs = new HashMap<>();
        for (int i = 1; i < parts.length; i++) {
            String k, v;
            String part = parts[i];
            int space = part.indexOf(' ');

            if (space == -1) {
                k = part.substring(1);
                v = "";
            } else {
                k = part.substring(1, space);
                v = part.substring(space + 1);
            }

            optionalArgs.put(k, v);
        }

        return new Parser(taskType, taskName, optionalArgs);
    }

    /**
     * Get command.
     * @return Command name.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Get first argument.
     * @return First argument.
     */
    public String getArg() {
        return this.argument;
    }

    /**
     * Get first argument as integer.
     * @return First argument cast to integer.
     * @throws DukeException If argument cannot be parsed to integer.
     */
    public int getArgAsInt() throws DukeException {
        if (this.argument == null) {
            throw new DukeException("No integer argument provided");
        }

        try {
            return Integer.parseInt(this.argument);
        } catch (NumberFormatException e) {
            throw new DukeException(String.format("Invalid integer argument: %s", this.argument));
        }
    }

    /**
     * Get optional argument value.
     * @param argName Name of argument to get.
     * @return Value of optional argument or null if not present.
     */
    public String getOptArg(String argName) {
        if (this.optionalArgs == null || !this.optionalArgs.containsKey(argName)) {
            return null;
        }

        return this.optionalArgs.get(argName);
    }

    /**
     * Get optional argument value as LocalDateTime.
     * @param argName Name of argument to get.
     * @return Value of optional argument as LocalDateTime or null if not present.
     * @throws DukeException If argument value cannot be parsed as LocalDateTime.
     */
    public LocalDateTime getOptArgAsDateTime(String argName) throws DukeException {
        String arg = getOptArg(argName);

        if (arg == null) {
            return null;
        }

        SimpleDateFormat dateFormat;

        if (arg.length() == 10) {
            dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        } else if (arg.length() == 15) {
            dateFormat = new SimpleDateFormat("yyyy/MM/dd HHmm");
        } else {
            throw new DukeException(String.format("Invalid date (%s), should be yyyy/MM/dd HHmm", arg));
        }

        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(arg);
        } catch (ParseException e) {
            throw new DukeException(String.format("Invalid date (%s), should be yyyy/MM/dd HHmm", arg));
        }

        return parsedDate
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}

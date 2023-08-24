import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.MissingDescriptionException;
import exceptions.UnknownCommandException;

/**
 * The Commands enum represents the different commands that can be executed by
 * the user.
 */
public enum Commands {
    /**
     * The different commands that can be done in the chatbot and their respective
     * string representation and their regex pattern when input the command.
     */
    LIST("list", "^list$"),
    MARK("mark", "^(mark) (\\d+)$"),
    UNMARK("unmark", "^(unmark) (\\d+)$"),
    TODO("todo", "^(todo)(?: (.*))?$"),
    DEADLINE("deadline", "^(deadline) (.+?) /by (.+)$"),
    EVENT("event", "^(event) (.+?) /from (.+?) /to (.+)$"),
    DELETE("delete", "^(delete) (\\d+)$");

    /**
     * The string representation of the command.
     */
    public final String commandString;

    /**
     * The pattern that the command takes.
     */
    public final Pattern commandPattern;

    /**
     * Constructs a new command with the specified string representation and one
     * parameter.
     *
     * @param commandString the string representation of the command
     * @param pattern the pattern that the command takes
     */
    private Commands(String commandString, String commandPattern) {
        this.commandString = commandString;
        this.commandPattern = Pattern.compile(commandPattern);
    }

    /**
     * Parses the raw input string and returns the corresponding command.
     *
     * @param rawInput the raw input string
     * @return the corresponding command, or null if the input is invalid
     */
    public static Commands parseCommand(String rawInput) throws UnknownCommandException {
        for (Commands c : values()) {
            Matcher matcher = c.commandPattern.matcher(rawInput);
            if (matcher.find()) {
                return c;
            }
        }
        throw new UnknownCommandException(rawInput);
    }

    /**
     * The following methods are used to extract the different parameters for the task commands.
     * 
     * @param input the raw input string
     * @return the corresponding parameter, or null if the input is invalid
     * @throws MissingDescriptionException
     */
    public static String extractTaskDescription(String input) throws MissingDescriptionException {
        //create a task pattern for the three tasks
        List<Commands> taskCommands = List.of(TODO, DEADLINE, EVENT);

        for (Commands taskCommand : taskCommands) {
            Matcher matcher = taskCommand.commandPattern.matcher(input);
            if (matcher.matches()) {
                String match = matcher.group(2);  // Store the description once a match is found
                if (match == null || match.trim().isEmpty()) {
                    throw new MissingDescriptionException(matcher.group(1));
                }
                return match;
            }
        }
        return null;
    }

    public static String extractDeadline(String input) {
        Matcher matcher = DEADLINE.commandPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(3); 
        }
        return null;
    }

    public static String extractEventFrom(String input) {
        Matcher matcher = EVENT.commandPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(3); 
        }
        return null;
    }

    public static String extractEventTo(String input) {
        Matcher matcher = EVENT.commandPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(4); 
        }
        return null;
    }
}
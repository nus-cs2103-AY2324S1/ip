import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    TODO("todo", "^(todo) (.+)$"),
    DEADLINE("deadline", "^(deadline) (.+?) /by (.+)$"),
    EVENT("event", "^(event) (.+?) /from (.+?) /to (.+)$");

    /**
     * The string representation of the command.
     */
    public final String commandString;

    /**
     * The pattern that the command takes.
     */
    public final Pattern patternString;

    /**
     * Constructs a new command with the specified string representation and one
     * parameter.
     *
     * @param commandString the string representation of the command
     * @param patternString the pattern that the command takes
     */
    private Commands(String commandString, String patternString) {
        this.commandString = commandString;
        this.patternString = Pattern.compile(patternString);
    }

    /**
     * Parses the raw input string and returns the corresponding command.
     *
     * @param rawInput the raw input string
     * @return the corresponding command, or null if the input is invalid
     */
    public static Commands parseCommand(String rawInput) {
        for (Commands c : values()) {
            Matcher matcher = c.patternString.matcher(rawInput);
            if (matcher.find()) {
                return c;
            }
        }
        return null;
    }

    /**
     * The following methods are used to extract the different parameters for the task commands.
     * 
     * @param input the raw input string
     * @return the corresponding parameter, or null if the input is invalid
     */
    public static String extractTaskDescription(String input) {
        //create a task pattern for the three tasks
        Pattern taskPattern = Pattern.compile("^(todo|deadline|event) (.+?)( /by (.+?))?( /from (.+?))?$");
        Matcher matcher = taskPattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(2); 
        }
        return null;
    }

    public static String extractDeadline(String input) {
        Matcher matcher = DEADLINE.patternString.matcher(input);
        if (matcher.find()) {
            return matcher.group(3); 
        }
        return null;
    }

    public static String extractEventFrom(String input) {
        Matcher matcher = EVENT.patternString.matcher(input);
        if (matcher.find()) {
            return matcher.group(3); 
        }
        return null;
    }

    public static String extractEventTo(String input) {
        Matcher matcher = EVENT.patternString.matcher(input);
        if (matcher.find()) {
            return matcher.group(4); 
        }
        return null;
    }
}
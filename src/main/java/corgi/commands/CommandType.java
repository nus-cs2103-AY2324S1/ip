package corgi.commands;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Types of commands that can be given to the Corgi chat bot.
 */
public enum CommandType {
    TODO("todo /desc [task]",
            new HashSet<>(Arrays.asList("/desc"))),
    DEADLINE("deadline /desc [task] /by [yyyy-mm-dd]",
            new HashSet<>(Arrays.asList("/desc", "/by"))),
    EVENT("event /desc [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]",
            new HashSet<>(Arrays.asList("/desc", "/from", "/to"))),
    MARK("mark /target [task no.]",
            new HashSet<>(Arrays.asList("/target"))),
    UNMARK("unmark /target [task no.]",
            new HashSet<>(Arrays.asList("/target"))),
    DELETE("delete /target [task no.]",
            new HashSet<>(Arrays.asList("/target"))),
    DATE("date /target [yyyy-mm-dd]",
            new HashSet<>(Arrays.asList("/target"))),
    FIND("find /target [keyword]",
            new HashSet<>(Arrays.asList("/target"))),
    BYE("bye",
            new HashSet<>()),
    LIST("list",
            new HashSet<>()),
    UNDO("undo",
            new HashSet<>());

    private final String commandFormat;
    private final Set<String> arguments;

    /**
     * Constructs a new CommandType with the given command format.
     *
     * @param commandFormat The command format
     */
    CommandType(String commandFormat, Set<String> arguments) {
        this.commandFormat = commandFormat;
        this.arguments = arguments;
    }

    /**
     * Retrieves the corresponding CommandType enum value based on the given command string.
     *
     * @param commandStr The command string to match
     * @return The matching CommandType enum value
     * @throws InvalidCommandException if the command string is not recognized
     */
    public static CommandType getCommandType(String commandStr) throws InvalidCommandException {
        switch (commandStr.toLowerCase()) {
        case "mark":
            return MARK;
        case "unmark":
            return UNMARK;
        case "todo":
            return TODO;
        case "deadline":
            return DEADLINE;
        case "event":
            return EVENT;
        case "bye":
            return BYE;
        case "list":
            return LIST;
        case "delete":
            return DELETE;
        case "date":
            return DATE;
        case "find":
            return FIND;
        case "undo":
            return UNDO;
        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Retrieves the command format string for this CommandType.
     *
     * @return The command format string.
     */
    public String getCommandFormat() {
        return "Format: \n" + this.commandFormat;
    }

    /**
     * Retrieves the arguments list for this CommandType.
     *
     * @return The set of arguments.
     */
    public Set<String> getArgumentsSet() {
        return this.arguments;
    }

}

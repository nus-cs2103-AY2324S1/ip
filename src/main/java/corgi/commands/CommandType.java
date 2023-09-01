package corgi.commands;

/**
 * Types of commands that can be given to the Corgi chat bot.
 */
public enum CommandType {
    MARK("mark [task no.]"), 
    UNMARK("unmark [task no.]"), 
    TODO("todo [task]"), 
    DEADLINE("deadline [task] /by [yyyy-mm-dd]"), 
    EVENT("event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]"), 
    BYE("bye"), 
    LIST("list"),
    DELETE("delete [task no.]"),
    DATE("date [yyyy-mm-dd]");

    private final String commandFormat;

    /**
     * Constructs a new CommandType with the given command format.
     * 
     * @param commandFormat The command format
     */
    CommandType(String commandFormat) {
        this.commandFormat = commandFormat;
    }

    /**
     * Retrieves the corresponding CommandType enum value based on the given command string.
     *
     * @param commandStr The command string to match
     * @return The matching CommandType enum value
     * @throws InvalidCommandException if the command string is not recognized
     */
    public static CommandType getCommandType(String commandStr) throws InvalidCommandException{
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
            default:
                throw new InvalidCommandException();
        }
    }

    /**
     * Retrieves the command format string for this CommandType.
     * 
     * @return The command format string
     */
    public String getCommandFormat() {
        return this.commandFormat;
    }

}

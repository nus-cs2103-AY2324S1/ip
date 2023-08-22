package commands;

/**
 * Types of commands that can be given to the Corgi chat bot.
 */
public enum CommandType {
    MARK, UNMARK, TODO, DEADLINE, EVENT, BYE, LIST;

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
            default:
                throw new InvalidCommandException();
        }
    }
}

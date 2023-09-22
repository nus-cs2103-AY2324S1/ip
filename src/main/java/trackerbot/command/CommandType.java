package trackerbot.command;

/**
 * Enumerates the possible Command types that exist in the Command class.
 * <p>CommandType should directly mirror the number of nested implementations of the
 * Command subclass, which can be instantiated with Command.of().</p>
 *
 * @author WZWren
 * @version A-JavaDoc
 * @see trackerbot.command.Command#of
 */
public enum CommandType {
    /** Command to declare a mass command. **/
    MASS("mass"),
    /** Command to exit the program. **/
    BYE("bye"),
    /** Command to list all tasks in the task list. **/
    LIST("list"),
    /** Command to add a new to-do task to the task list. **/
    TODO("todo"),
    /** Command to add a new deadline task to the task list. **/
    DEADLINE("deadline"),
    /** Command to add a new event task to the task list. **/
    EVENT("event"),
    /** Command to mark a task to be complete. **/
    MARK("mark"),
    /** Command to mark a task as incomplete. **/
    UNMARK("unmark"),
    /** Command to delete a task. **/
    DELETE("delete"),
    /** Command to search for a task. **/
    FIND("find"),
    /** Command to denote an unknown keyword call. **/
    UNKNOWN("");

    /** The String representation of the enum. Used to parse the command into enum. **/
    private final String keyword;

    /**
     * Constructs the enum object of Command.
     *
     * @param keyword The keyword of the task.
     */
    CommandType(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets the keyword of the enum CommandType.
     *
     * @return The associated String keyword with the Command.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Parses the given string into a valid enum type.
     *
     * @param keyword The String to parse into a CommandType.
     * @return The associated command type with the given keyword.
     */
    public static CommandType getCommandType(String keyword) {
        CommandType result = CommandType.UNKNOWN;
        for (CommandType command: CommandType.values()) {
            if (keyword.equals(command.getKeyword())) {
                result = command;
                break;
            }
        }
        return result;
    }
}

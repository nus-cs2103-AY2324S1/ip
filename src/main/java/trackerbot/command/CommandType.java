package trackerbot.command;

/**
 * Enumerates the possible Command types that exist in the Command class.
 * <p>CommandType should directly mirror the number of nested implementations of the
 * Command subclass.</p>
 * @author WZWren
 * @version A-JavaDoc
 */
public enum CommandType {
    /** Command to exit the program. **/
    BYE ("bye"),
    /** Command to list all tasks in the task list. **/
    LIST ("list"),
    /** Command to add a new to-do task to the task list. **/
    TODO ("todo"),
    /** Command to add a new deadline task to the task list. **/
    DEADLINE ("deadline"),
    /** Command to add a new event task to the task list. **/
    EVENT ("event"),
    /** Command to mark a task to be complete. **/
    MARK ("mark"),
    /** Command to mark a task as incomplete. **/
    UNMARK ("unmark"),
    /** Command to delete a task. **/
    DELETE ("delete"),
    /** Command to denote an unknown keyword call. **/
    UNKNOWN ("");

    /** The String representation of the enum. Used to parse the command into enum. **/
    private final String keyword;

    /**
     * Constructor for the enum Command.
     * <p>Enum constructors are implicitly private, so the tag is not included.</p>
     * @param keyword The keyword of the task.
     */
    CommandType(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Gets the keyword of the enum CommandType.
     * @return The associated String keyword with the Command.
     */
    public String getKeyword() {
        return keyword;
    }
}

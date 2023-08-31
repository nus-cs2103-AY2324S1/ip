package duke.command;

/**
 * The Command enum for the Chatbot.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    /**
     * The String value of the command.
     */
    private String value;

    /**
     * The Constructor for the Command class.
     *
     * @param value The String value of the command.
     */
    Command(String value) {
        this.value = value;
    }

    /**
     * Returns the respective command of the input string.
     *
     * @param value The input String containing the command.
     * @return The respective command of the input string.
     */
    public static Command getCommand(String value) {
        for (Command cmd : Command.values()) {
            if (cmd.value.equals(value)) {
                return cmd;
            }
        }
        return null;
    }

    /**
     * Returns the String value of the command.
     *
     * @return The String value of the command.
     */
    public String getCommandName() {
        return this.value;
    }
}
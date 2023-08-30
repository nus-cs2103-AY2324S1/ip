package duke.commands;

import duke.exceptions.DukeInvalidCommandException;

/**
 * The command enum is used to store the valid commands that LilBro can
 * understand.
 */
public enum CommandType {
    EXIT("bye"),
    LIST_TASKS("list"),
    ADD_TODO("todo"),
    ADD_DEADLINE("deadline"),
    ADD_EVENT("event"),
    MARK_TASK("mark"),
    UNMARK_TASK("unmark"),
    DELETE_TASK("delete"),
    FIND_TASK("find");

    // The string representation of the command.
    private final String value;

    /**
     * The constructor is used to create a new command with the given value.
     *
     * @param value The string representation of the command.
     */
    private CommandType(String value) {
        this.value = value;
    }

    /**
     * The fromString method is used to get the command from the given string.
     *
     * @param value The string representation of the command.
     * @return The command if it exists, null otherwise.
     */
    public static CommandType fromString(String value) throws DukeInvalidCommandException {
        for (CommandType command : CommandType.values()) {
            if (command.value.equalsIgnoreCase(value)) {
                return command;
            }
        }
        throw new DukeInvalidCommandException(
                "Not sure what you're blabbering on about, please use a valid command.");
    }
}

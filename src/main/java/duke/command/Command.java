package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {

    /**
     * Represents the type of command.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BYE, FIND, INVALID
    }

    public CommandType type;

    /**
     * Initialises a Command object with the given type.
     *
     * @param type The type of command.
     */
    public Command(CommandType type) {
        this.type = type;
    }

    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param ui    The user interface.
     * @return The response to the user input.
     */
    public abstract String execute(TaskList tasks, Ui ui);
}

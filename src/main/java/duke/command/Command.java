package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The Command class represents a command in the Duke application.
 * It is an abstract class that defines the structure of all specific command classes.
 * Specific command classes must extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Executes the specific command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving and loading tasks (not used in all commands).
     * @return A string message indicating the result of executing the command.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}

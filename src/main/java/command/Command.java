package command;

import duke.Storage;
import duke.TaskList;

import exceptions.DukeException;

/**
 * The base class for all command implementations in the Duke application.
 * Represents a command that can be executed on the task list.
 */
public abstract class Command {
    private Boolean isExit;

    /**
     * Constructs a Command object.
     *
     * @param isExit Indicates whether the command is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the command on the specified task list, storage, and user interface.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler for reading/writing tasks.
     * @throws DukeException If there is an error executing the command.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;
}

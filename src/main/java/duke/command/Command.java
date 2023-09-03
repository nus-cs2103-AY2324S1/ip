package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents an abstract command that can be executed by the chatbot.
 */
public abstract class Command {

    /**
     * Executes the command's action.
     *
     * @param taskList The list of tasks that the command operates on.
     * @param ui The user interface instance to interact with the user.
     * @param storage The storage instance to manage data.
     * @throws DukeException If an exception specific to Duke's operations occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}

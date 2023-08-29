package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an abstract command that can be executed by the Duke chatbot.
 * Provides an execute method and an isExit method that must be implemented by subclasses.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage component.
     *
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage component.
     * @throws DukeException If an error occurs while executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean indicating whether the command is an exit command.
     *
     * @return A boolean indicating whether the command is an exit command.
     */
    public abstract boolean isExit();
}


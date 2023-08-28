package duke.command;

import duke.tasks.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * Represents an abstract duke.command that can be executed in the application.
 */
public abstract class Command {

    /**
     * Executes the specific duke.command using the provided task list, user interface, and storage system.
     *
     * @param tasks List of duke.tasks.
     * @param ui User interface.
     * @param storage Storage system.
     * @throws Exception If there's any error during the duke.command execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Checks if this duke.command triggers an exit from the application.
     *
     * @return {@code true} if the duke.command causes the application to exit, otherwise {@code false}.
     */
    public abstract boolean isExit();
}

package duke.command;

import duke.alias.AliasMap;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `Command` class represents a command that can be executed by the Duke application.
 */
public abstract class Command {

    /**
     * A flag indicating whether executing this command should cause the application to exit.
     */
    protected boolean willExitNext = false;

    /**
     * Executes the command, performing the associated action on the task list, UI, and storage.
     *
     * @param items   The task list to operate on.
     * @param aliases The aliasMap to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage component for reading/writing data.
     * @return The text response by Duke.
     * @throws DukeException If an error occurs while executing the command.
     */
    public abstract String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if executing this command should cause the application to exit.
     *
     * @return `true` if the application should exit after executing this command, `false` otherwise.
     */
    public boolean willExitNext() {
        return willExitNext;
    }
}

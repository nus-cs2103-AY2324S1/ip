package commands;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The abstract Command class represents a command that can be run
 */
public abstract class Command {

    /**
     * Executes the command to perform a specific action.
     *
     * @param taskList The task list that the command may operate on.
     * @param ui       The user interface for displaying messages or user interactions.
     * @param storage  The storage component for reading or writing data.
     */
    public abstract void runCommand(TaskList taskList, Ui ui, Storage storage);
}

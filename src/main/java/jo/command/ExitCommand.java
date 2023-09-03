package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

/**
 * The `ExitCommand` class represents a command for exiting the `Jo` application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command, triggering the application to exit and displaying a farewell message.
     * @param tasks   The `TaskList` containing tasks to operate on (unused in this command).
     * @param ui      The user interface for displaying messages and handling the exit.
     * @param storage The storage object for loading and saving tasks to a file (unused in this command).
     * @throws JoException If an error occurs during the execution of the command (unused in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.sayBye();
    }

    /**
     * Checks whether the command results in exiting the application.
     * @return `true` since this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package commands;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
/**
 * The `ExitCommand` class represents a command to exit the application.
 * When executed, it triggers the application to exit gracefully.
 */
public class ExitCommand extends Command {
    /**
     * Executes the `ExitCommand` by instructing the user interface to handle the exit action.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface responsible for handling the exit action.
     * @param storage  The storage component (not used in this command).
     */
    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        ui.exit(taskList, storage);
    }
}

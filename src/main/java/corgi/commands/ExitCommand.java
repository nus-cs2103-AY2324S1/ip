package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.Ui;

/**
 * Represents a command to exit the application.
 * This command terminates the application and provides an exit message to the user.
 */
public class ExitCommand extends Command{

    /**
     * Initializes a new ExitCommand instance.
     */
    public ExitCommand() {
        super(true, CommandType.BYE);
    }

    /**
     * Executes the command by displaying an exit message to the user, indicating that the application is terminating.
     * 
     * @param list The task list (not used in this command).
     * @param ui The user interface for displaying the exit message.
     * @param storage The storage for saving and loading tasks (not used in this command).
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage<Task> storage) {
        ui.showExitMsg();
    }
}

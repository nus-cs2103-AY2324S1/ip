package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;

/**
 * Represents a command to exit the application.
 * This command terminates the application and provides an exit message to the user.
 */
public class ExitCommand extends Command {

    /**
     * Initializes a new ExitCommand instance.
     */
    public ExitCommand() {
        super(true, CommandType.BYE);
    }

    /**
     * Executes the command by return an exit message to the user, indicating that the application is terminating.
     *
     * @param list The task list (not used in this command).
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (not used in this command).
     */
    @Override
    public String execute(TaskList list, TextRenderer renderer, Storage<Task> storage) {
        return renderer.showExitMsg();
    }
}

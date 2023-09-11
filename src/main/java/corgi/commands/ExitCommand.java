package corgi.commands;

import java.util.Stack;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to exit the application.
 * This command terminates the application and provides an exit message to the user.
 */
public class ExitCommand extends Command {

    /**
     * Initializes a new ExitCommand instance.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the command by return an exit message to the user, indicating that the application is terminating.
     *
     * @param list The task list (not used in this command).
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (not used in this command).
     * @param history The history stack to store the states.
     * @return A string message indicating the result of the command execution.
     */
    @Override
    public String execute(
        TaskList list, TextRenderer renderer, Storage<Task> storage, Stack<Pair<Command, TaskList>> history) {
        return renderer.showExitMsg();
    }
}

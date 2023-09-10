package corgi.commands;

import java.util.function.Predicate;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;

/**
 * Represents a command to find tasks containing a specific keyword in the task list.
 */
public class FindTasksContainKeywordCommand extends Command {
    /**
     * The predicate used to filter tasks by keyword.
     */
    private Predicate<Task> predicate;

    /**
     * The target keyword for finding tasks.
     */
    private final String target;

    /**
     * Initializes a new FindTasksContainKeywordCommand instance with the target keyword.
     *
     * @param target The target keyword.
     */
    public FindTasksContainKeywordCommand(String target) {
        super(false);
        this.target = target;

        // Define a predicate to filter tasks based on whether they contain the keyword.
        this.predicate = t -> t.contains(target);
    }

    /**
     * Executes the command by filtering the task list based on the given
     * predicate to find tasks containing specific keyword.
     * It then return the filtered tasks to the user or a message indicating
     * that no matching tasks were found.
     *
     * @param list The task list to filter.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (not used in this command).
     */
    @Override
    public String execute(TaskList list, TextRenderer renderer, Storage<Task> storage) {
        TaskList tasksContainKeyword = list.filter(predicate);

        if (tasksContainKeyword.isEmpty()) {
            return renderer.showKeywordNotFound(this.target);
        } else {
            return renderer.showTasksWithKeyword(this.target, tasksContainKeyword.toString());
        }
    }
}

package corgi.commands;

import java.util.Stack;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.tasks.TaskListIndexOutOfBoundsException;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to delete a task from the task list.
 * This command deletes a task at the specified index from the task list.
 */
public class DeleteTaskCommand extends Command {
    /**
     * The index of the task to be deleted.
     */
    private int targetIdx;

    /**
     * Initializes a new DeleteTaskCommand instance with the specified target index.
     *
     * @param targetIdx The index of the task to be deleted.
     */
    public DeleteTaskCommand(int targetIdx) {
        super(false);
        this.targetIdx = targetIdx;
    }

    /**
     * Executes the command by deleting the task at the specified index from the task list,
     * saving the updated list to storage, store the state to history stack, 
     * and return message indicating that the task has been deleted.
     *
     * @param list The task list from which the task should be deleted.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (if applicable).
     * @param history The history stack to store the states.
     * @throws CommandExecutionException If an error occurs during command execution.
     * @return A string message indicating the result of the command execution.
     */
    @Override
    public String execute(
            TaskList list, TextRenderer renderer, Storage<Task> storage, Stack<Pair<Command, TaskList>> history)
            throws CommandExecutionException {
        try {
            String targetTaskInfo = list.getTaskInfo(targetIdx);
            list.remove(targetIdx);

            storage.save(list);

            Pair<Command, TaskList> currState = new Pair<>(this, list);
            history.push(currState);

            return renderer.showTaskDeleted(targetTaskInfo, list.size());
        } catch (TaskListIndexOutOfBoundsException e) {
            throw new CommandExecutionException("Invalid index provided!");
        }
    }
}

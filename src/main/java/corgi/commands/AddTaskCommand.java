package corgi.commands;

import java.util.Stack;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;
import javafx.util.Pair;

/**
 * Represents a command to add a task to the task list.
 * This command adds a specified task to the task list.
 * The type of task (todo, deadline, or event) is determined by the command type.
 */
public class AddTaskCommand extends Command {
    /**
     * The task to be added.
     */
    private Task target;

    /**
     * The type of task (todo, deadline, or event) being added.
     */
    private String taskType;

    /**
     * Initializes a new AddTaskCommand instance with the specified task and command type.
     *
     * @param target The task to be added.
     * @param type The type of command (CommandType.TODO, CommandType.DEADLINE, or CommandType.EVENT).
     */
    public AddTaskCommand(Task target) {
        super(false);
        this.target = target;
    }

    /**
     * Executes the command by adding the specified task to the task list, saving the updated list to storage,
     * store the stack to the history stack and return formatted message indicating that the task has been added.
     *
     * @param list The task list to which the task should be added.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (if applicable).
     * @param history The history stack to store the states.
     * @return A string message indicating the result of the command execution.
     */
    @Override
    public String execute(
            TaskList list, TextRenderer renderer, Storage<Task> storage, Stack<Pair<Command, TaskList>> history) {
        list.add(this.target);
        storage.save(list);

        Pair<Command, TaskList> currState = new Pair<>(this, list);
        history.push(currState);

        return renderer.showTaskAdded(this.taskType, target.toString(), list.size());
    }

    @Override
    public String toString() {
        return "Add task " + this.target;
    }
}

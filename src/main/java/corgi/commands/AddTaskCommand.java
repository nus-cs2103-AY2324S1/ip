package corgi.commands;

import corgi.storage.Storage;
import corgi.tasks.Task;
import corgi.tasks.TaskList;
import corgi.ui.TextRenderer;

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
    public AddTaskCommand(Task target, CommandType type) {
        super(false, type);
        this.target = target;
        if (type == CommandType.TODO) {
            this.taskType = "todo";
        } else if (type == CommandType.DEADLINE) {
            this.taskType = "deadline";
        } else {
            this.taskType = "event";
        }
    }

    /**
     * Executes the command by adding the specified task to the task list, saving the updated list to storage,
     * and return formatted message indicating that the task has been added.
     *
     * @param list The task list to which the task should be added.
     * @param renderer The text renderer to return formatted message.
     * @param storage The storage for saving and loading tasks (if applicable).
     */
    @Override
    public String execute(TaskList list, TextRenderer renderer, Storage<Task> storage) {
        list.add(this.target);
        storage.save(list);
        return renderer.showTaskAdded(this.taskType, target.toString(), list.size());
    }
}

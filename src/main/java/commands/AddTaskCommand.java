package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * This command adds a specified task to the task list.
 * The type of task (todo, deadline, or event) is determined by the command type.
 */
public class AddTaskCommand extends Command{
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
     * and displaying a message to the user indicating that the task has been added.
     *
     * @param list The task list to which the task should be added.
     * @param ui The user interface for displaying feedback to the user.
     * @param storage The storage for saving and loading tasks (if applicable).
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage<Task> storage) {
        list.add(this.target);
        storage.save(list);
        ui.showTaskAdded(this.taskType, target.toString(), list.size());
    }
}

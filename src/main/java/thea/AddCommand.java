package thea;

/**
 * Represents an executable user command which adds a new task to a taskList.
 * This class has data on the task type and the Task that needs to be added to a taskList.
 * This class is a subclass of the abstract class Command with abstract method execute.
 */
public class AddCommand extends Command {
    private String taskType;
    private Task task;

    /**
     * Constructs a new AddCommand object.
     *
     * @param taskType type of task to be added.
     * @param task task to be added.
     */
    public AddCommand(String taskType, Task task) {
        super(false);
        this.taskType = taskType;
        this.task = task;
    }

    /**
     * Add the new task to the task list.
     *
     * @param tasks list of current tasks.
     * @param ui Ui class that deals with user interaction.
     * @param storage Storage class that deals with saving data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.taskAdded(task, tasks);
        storage.saveTaskList(tasks);
    }
}

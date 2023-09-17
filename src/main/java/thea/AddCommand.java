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
     * @param thea reference to the chatbot containing relevant data.
     */
    @Override
    public String execute(Thea thea) {
        thea.tasks.add(task);
        thea.storage.saveTaskList(thea.tasks);
        return thea.ui.taskAdded(task, thea.tasks);
    }
}

package veneto.command;

import veneto.task.Task;
import veneto.task.TaskList;

public class DeleteCommand extends Command {
    /* Fields */
    public static final String type = "delete";

    private int taskId;
    private Task removedTask;

    /* Constructor */
    public DeleteCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    /* Methods */
    /**
     * the DeleteCommand operates
     * @param tasks the TaskList that the DeleteCommand operates on
     */
    @Override
    public void op(TaskList tasks) {
        removedTask = tasks.remove(taskId-1);
        tasks.storageChanged = 1;
    }

    @Override
    /**
     * @return the String representation of the task removed to the TaskList
     */
    public String toString() {
        return removedTask.toString();
    }

    /**
     * @return the type of the Command
     */
    public String getType() {
        return type;
    }
}

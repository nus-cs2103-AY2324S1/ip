package veneto.command;

import veneto.task.Task;
import veneto.task.TaskList;

public class DeleteCommand extends Command {
    /** Fields */
    public static final String type = "delete";

    private int taskId;
    private Task removedTask;

    /** Constructor */
    public DeleteCommand(int taskId) {
        super();
        this.taskId = taskId;
    }

    /** Methods */
    @Override
    public void op(TaskList tasks) {
        removedTask = tasks.remove(taskId-1);
        tasks.storageChanged = 1;
    }

    @Override
    public String toString() {
        return removedTask.toString();
    }

    public static String getType() {
        return type;
    }
}

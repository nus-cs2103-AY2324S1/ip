package veneto.command;

import veneto.exceptions.VenetoException;
import veneto.task.Task;
import veneto.task.TaskList;

public class MarkCommand extends Command {
    /* Fields */
    public static final String type = "add";
    private int taskId;

    /**
     * This variable indicates if the function is "mark()" or "unmark()"
     * funcId == 1 -> mark() ; funcId = 0 -> unmark()
     */
    private int funcId;
    private Task currTask;

    /* Constructor */
    public MarkCommand(int taskId, int funcId) {
        super();
        this.taskId = taskId;
        this.funcId = funcId;
    }

    /* Methods */
    /**
     * the MarkCommand operates
     * @param tasks the TaskList that the MarkCommand operates on
     */
    @Override
    public void op(TaskList tasks) throws VenetoException {
        currTask = tasks.get(taskId - 1);
        currTask.mark(funcId);
        tasks.storageChanged = 1;
    }

    @Override
    /**
     * @return the String representation of the task marked or unmarked
     */
    public String toString() {
        return currTask.toString();
    }

    /**
     * @return the type of the Command
     */
    public String getType() {
        return type;
    }
}

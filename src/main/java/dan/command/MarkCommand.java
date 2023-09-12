package dan.command;

import dan.exceptions.DanException;
import dan.task.Task;
import dan.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    /** Fields */
    public static final String type = "add";
    private int taskId;

    /**
     * This variable indicates if the function is "mark()" or "unmark()"
     * funcId == 1 -> mark() ; funcId = 0 -> unmark()
     */
    private int funcId;
    private Task currTask;

    /** Constructor */
    public MarkCommand(int taskId, int funcId) {
        super();
        this.taskId = taskId;
        this.funcId = funcId;
    }

    /** Methods */
    @Override
    public void op(TaskList tasks) throws DanException {
        currTask = tasks.get(taskId - 1);
        currTask.mark(funcId);
        tasks.storageChanged = 1;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return currTask.toString();
    }
}

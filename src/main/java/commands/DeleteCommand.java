package commands;

import tasks.TaskList;

/**
 * This class instructs the application to delete a task from the task list.
 */
public class DeleteCommand implements Command {

    public static final String COMMAND_WORD = "delete";
    private int taskNumber;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNum
     */
    public DeleteCommand(int taskNum) {
        this.taskNumber = taskNum;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.deleteTask(taskNumber);
    }
}

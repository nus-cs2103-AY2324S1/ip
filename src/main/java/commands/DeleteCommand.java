package commands;

import tasks.TaskList;

/**
 * This class instructs the application to delete a task from the task list.
 */
public class DeleteCommand extends Command {

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
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskNumber);
    }
}

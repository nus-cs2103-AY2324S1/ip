package commands;

import tasks.TaskList;

/**
 * This class instructs the application to mark a task as done.
 */
public class MarkCommand implements Command {

    public static final String COMMAND_WORD = "mark";
    private int taskNumber;

    /**
     * Constructs a MarkCommand object.
     *
     * @param taskNum
     */
    public MarkCommand(int taskNum) {
        this.taskNumber = taskNum;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            return tasks.markTaskDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}

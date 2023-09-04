package commands;

import tasks.TaskList;

/**
 * This class instructs the application to mark a task as not done.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private int taskNumber;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param taskNum
     */
    public UnmarkCommand(int taskNum) {
        this.taskNumber = taskNum;
    }

    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.unmarkTaskDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}

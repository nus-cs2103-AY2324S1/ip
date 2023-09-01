package commands;

import tasks.TaskList;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private int taskNumber;

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

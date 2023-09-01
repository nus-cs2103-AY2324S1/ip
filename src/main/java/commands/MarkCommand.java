package commands;

import tasks.TaskList;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int taskNumber;

    public MarkCommand(int taskNum) {
        this.taskNumber = taskNum;
    }

    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.markTaskDone(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}

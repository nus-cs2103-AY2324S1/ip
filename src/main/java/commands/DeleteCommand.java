package commands;

import tasks.TaskList;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int taskNumber;

    public DeleteCommand(int taskNum) {
        this.taskNumber = taskNum;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskNumber);
    }
}

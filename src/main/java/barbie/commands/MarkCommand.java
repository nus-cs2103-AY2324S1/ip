package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Task;

/**
 * Represents the command when a "mark" is called by the user.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs an inctsnace of a MarkCommand.
     * Saves the taskNumber to mark
     * @param taskNumber the task to mark
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.isExit = false;

    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return String to be returned to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        Task task = taskList.get(taskNumber);
        task.mark();
        Storage.changeLineStatus("1", taskNumber);
        return Ui.mark(task);
    }
}

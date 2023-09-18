package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Task;

/**
 * Represents a command when a "unmark" is called by the user.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs an instanceof a UnmarkCommand, saves the taskNumber.
     * @param taskNumber task to be unmarked
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.isExit = false;
    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application
     * @param taskList current list of tasks
     * @return String to be returned to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        Task task = taskList.get(taskNumber);
        task.unmark();
        Storage.changeLineStatus("0", taskNumber);
        return Ui.unmark(task);
    }
}

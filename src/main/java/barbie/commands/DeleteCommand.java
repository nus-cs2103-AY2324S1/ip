package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.exceptions.BarbieListEmptyException;
import barbie.types.Task;

/**
 * Represents the command when a "del" is called by the user.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs an instance of a DeleteCommand, and saves the taskNumber to delete.
     * @param taskNumber the task number to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.isExit = false;
    }

    /**
     * Runs the command of this instance of Command.
     * (eg ExitCommand will contain the logic of exiting the application)
     * @param taskList current list of tasks
     * @return String to return to user
     */
    @Override
    public String run(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            return new BarbieListEmptyException().getMessage();
        }

        String taskToDel = taskList.get(taskNumber).toString();
        taskList.remove(this.taskNumber);
        Storage.deleteLine(taskNumber);
        return Ui.del() + "\n" + taskToDel;
    }
}

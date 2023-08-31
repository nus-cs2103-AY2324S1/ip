package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command to delete a task from the task list
 */
public class DeleteCommand extends Command {
    /**
     * Task number to be deleted
     */
    private int taskNum;

    /**
     * Constructor for DeleteCommand
     * @param taskNum Task number to be deleted
     */
    public DeleteCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to delete a task from the task list
     * @param tasks Task list to be deleted from
     * @param ui Ui to show the user the task has been deleted
     * @param storage Storage to save the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        tasks.delete(taskNum);
        ui.showLine();
    }
}

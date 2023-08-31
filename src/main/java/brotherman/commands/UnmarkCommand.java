package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a command to unmark a task in the task list
 */
public class UnmarkCommand extends Command {
    /**
     * Task number to be unmarked
     */
    private int taskNum;

    /**
     * Constructor for UnmarkCommand
     * @param taskNum Task number to be unmarked
     */
    public UnmarkCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    /**
     * Executes the command to unmark a task in the task list
     * @param tasks Task list to be unmarked
     * @param ui Ui to show the user the task has been unmarked
     * @param storage Storage to save the task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markUndone(taskNum);
    }
}

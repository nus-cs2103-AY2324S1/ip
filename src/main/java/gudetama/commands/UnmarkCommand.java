package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command to unmark a previous marked task in the task list
 */
public class UnmarkCommand extends Command {
    /**
     * Index of task to be unmarked
     */
    private final int num;

    /**
     * Constructor for UnmarkCommand class
     * @param num Index of the task to be unmarked.
     */
    public UnmarkCommand(int num) {
        assert num > 0 : "Task index should be greater than 0.";
        this.num = num;
    }

    /**
     * Executes the command that unmarks the task at the specified index
     * @param tasksList Task list to be unmarked
     * @param ui A UI instance that displays a message to indicate to the user the task has been unmarked
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        return tasksList.unMarkTask(num);
    }
}

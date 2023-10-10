package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command to mark a previous marked task in the task list
 */
public class MarkCommand extends Command {

    /**
     * Index of task to be unmarked
     */
    private final int num;

    /**
     * Constructor for MarkCommand class
     * @param num Index of the task to be unmarked.
     */
    public MarkCommand(int num) {
        assert num > 0 : "Task index should be greater than 0.";
        this.num = num;
    }

    /**
     * Executes the command that marks the task at the specified index
     * @param tasksList Task list to be marked
     * @param ui A UI instance that displays a message to indicate to the user the task has been marked
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        return tasksList.markTask(num);
    }
}

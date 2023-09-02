package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

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
        this.num = num;
    }

    /**
     *  Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command that unmarks the task at the specified index
     * @param tasksList Task list to be unmarked
     * @param ui A UI instance that displays a message to indicate to the user the task has been unmarked
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        tasksList.unMarkTask(num);
    }
}

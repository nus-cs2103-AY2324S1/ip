package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to mark a previous marked task in the task list
 */
public class MarkCommand extends Command{

    /**
     * Index of task to be unmarked
     */
    private final int num;

    /**
     * Constructor for MarkCommand class
     * @param num Index of the task to be unmarked.
     */
    public MarkCommand(int num){
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
     * Executes the command that marks the task at the specified index
     * @param tasksList Task list to be marked
     * @param ui A UI instance that displays a message to indicate to the user the task has been marked
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        tasksList.markTask(num);
    }
}

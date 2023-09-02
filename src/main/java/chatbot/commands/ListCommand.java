package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to list the tasks in the task list
 */
public class ListCommand extends Command{
    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to list the tasks in the task list
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays the user the task list
     * @param storage Storage instance that saves the task to the task list
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
            ui.showList(tasksList);
    }
}

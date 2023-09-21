package avalon.command;

import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

/**
 * Represents a command to exit the Avalon application.
 * This command is triggered by user input "bye".
 */
public class ExitCommand extends Command{

    /**
     * Executes the ExitCommand, saving tasks to storage and displaying a farewell message.
     *
     * @param taskList The TaskList to save tasks to storage.
     * @param storage  The Storage instance for saving tasks.
     * @param ui       The Ui instance for displaying a farewell message.
     * @return A string message indicating the result of the command's execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        storage.saveTasks(taskList);
        ui.byeMessage();
        return ui.getOutput();
    }
}

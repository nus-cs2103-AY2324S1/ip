package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.gui.Ui;
import jarvis.tasks.TaskList;

/**
 * Represents the exit command for the Jarvis app.
 */
public class ByeCommand implements Command {

    /**
     * Executes the bye command by displaying a farewell message, saving tasks, and
     * exiting the application.
     *
     * @param taskList The TaskList containing the tasks.
     * @param ui       The Ui for user interface interactions.
     * @param storage  The Storage for saving tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        storage.saveTasks(taskList.getTaskList());
        System.exit(0);
    }
}

package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

/**
 * Represents the command to exit the program
 */
public class ExitCommand extends Command {
    /**
     * Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command to exit the program
     * @param tasksList Task list which contains the tasks
     * @param ui        A UI instance that displays a message to the user
     * @param storage   Storage instance that represents the storage of the file
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        storage.saveToFile(tasksList.retrieveList());
        ui.showGoodbye();
    }
}

package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The `ByeCommand` class represents a command to exit the Duke chatbot application.
 * It is responsible for saving task data and generating a farewell message.
 */
public class ByeCommand extends Command {

    /**
     * Executes the "Bye" command. It saves the current task data to storage and returns a farewell message.
     *
     * @param storage  The storage component responsible for saving task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A farewell message indicating the application is exiting.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        storage.updateStorage(taskList);
        return ui.exitReply();
    }
}

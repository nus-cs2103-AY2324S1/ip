package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The abstract `Command` class serves as the base class for all specific commands in the Duke chatbot application.
 * Subclasses should implement the `execute` method to define the behavior of individual commands.
 */
public abstract class Command {
    /**
     * Executes the specific behavior associated with this command.
     *
     * @param storage  The storage component responsible for saving and loading task data.
     * @param taskList The task list containing the tasks to be operated on.
     * @param ui       The user interface for displaying messages to the user.
     * @return A response message indicating the result of executing the command.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui);

}

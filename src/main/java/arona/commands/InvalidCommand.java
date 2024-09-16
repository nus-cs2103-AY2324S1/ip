package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * Initializes a new instance of the InvalidCommand class due to an invalid command keyword.
     *
     * @param taskList The task list to search within.
     * @param ui       The user interface for displaying messages.
     */
    public InvalidCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * Executes the InvalidCommand.
     *
     * @return A string message indicating that the command was invalid.
     */
    public String execute() {
        return ui.showInvalidArgumentMessage();
    }

}

package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that represents a command that is not recognized or pre-set in the Chatty system.
 * When the user enters an invalid or unrecognized command, an instance of this class is created
 * to handle and display an error message.
 */
public class CommandNotFound extends Command {

    /**
     * Constructor for the invalid command instance.
     * Initializes the instance to represent a non-exit command.
     */
    public CommandNotFound() {
        super(false);
    }

    /**
     * Executes the command for handling an invalid command.
     * Displays an error message through the user interface.
     *
     * @param taskList The task list with the current available tasks (not used in this command).
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class that is responsible for updating stored data (not used in this command).
     * @return A String containing the error message indicating that the input command is not valid.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showInvalid();
    }
}

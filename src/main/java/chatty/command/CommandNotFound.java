package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles command that is not pre-set in the system
 */
public class CommandNotFound extends Command {

    /**
     * Constructor for the invalid command
     */
    public CommandNotFound() {
        super(false);
    }

    /**
     * Prints out the error message
     * @param taskList the tasklist with the current available task
     * @param ui the current user interface
     * @param storage the storage class that is responsible for updating the stored list when necessary
     * @return the error message that tells the user that the input command is not valid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showInvalid();
    }
}

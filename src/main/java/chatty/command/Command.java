package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * An abstract class that manage the command from the user
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Constructor for Command class that takes in the state of the command that the user input
     * @param isExit the state of the command entered by user
     */
    public Command(Boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Return the state of the command
     * @return a boolean value that represent the state of the command
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Carry out each task according to the command entered
     * @param taskList the tasklist with the current available task
     * @param ui the current user interface
     * @param storage the storage class that is responsible for updating the stored list when necessary
     * @return the respective String after the action is carried out
     * @throws ChattyException when the sotrage class couldn't save the data
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException;
}

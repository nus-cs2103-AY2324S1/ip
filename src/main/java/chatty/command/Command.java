package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * An abstract class representing a command that can be executed by the Chatty chatbot.
 * Commands are used to interact with the user, manipulate the task list, and manage the user interface.
 * Each concrete subclass of this class defines specific behavior for a particular command type.
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Constructs a new command instance.
     *
     * @param isExit true if this command represents an exit command, false otherwise.
     */
    public Command(Boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return true if this command represents an exit command, false otherwise.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the command's specific behavior using the provided task list, user interface, and storage.
     * The concrete implementations of this method define the action to be taken for each command type.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage responsible for updating stored data, if necessary.
     * @return A String representing the result or response of executing the command.
     * @throws ChattyException if the storage class encounters an error while saving data.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException;
}

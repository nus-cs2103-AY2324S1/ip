package bob.command;

import bob.exception.BobException;
import bob.exception.BobInvalidTaskNumberException;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextUi;

/**
 * A command encapsulates logic that can be executed to interact with the application.
 * It can be stored an executed at a later instance or be reused.
 */
public abstract class Command {
    /**
     * Executes the current command and can modify the current state of the task list or
     * print out messages.
     *
     * @param taskList Current list of tasks
     * @param storageFile Storage file reader and writer
     * @param ui User Interface reader and writer
     * @throws BobException if the command is constructed with invalid arguments
     */
    public abstract void execute(TaskList taskList, StorageFile storageFile) throws BobException;

    /**
     * Represents whether the current command should terminate the current application.
     *
     * @return a boolean signifying whether to terminate the program
     */
    public abstract boolean isExit();

    /**
     * Returns a message to be displayed to the user after command is executed
     * successfully. This method should only be called after the execute method
     * has been called.
     *
     * @return Message to be displayed
     */
    public abstract String getOutputMessage() throws BobException;
}

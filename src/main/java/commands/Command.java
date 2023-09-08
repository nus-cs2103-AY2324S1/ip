package commands;

import functions.*;
import java.io.IOException;

/**
 * The base abstract class for different types of commands.
 */
public abstract class Command {

    /**
     * Executes the specific command on the task list, interacts with the user interface, and manages storage.
     *
     * @param tasks    The TaskList to perform the command on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage interface for saving and loading data.
     * @throws IOException If an I/O error occurs while interacting with storage.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Checks if the command is an exit command.
     *
     * @return Returns true if the command is an exit command, and false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}


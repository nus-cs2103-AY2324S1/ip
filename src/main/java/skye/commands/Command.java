package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.exception.DukeException;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the abstraction of a command to the chatbot.
 * <p>
 * It contains an unimplemented execute method for its subclasses to implement how the command
 * would interact with the TaskList, UI and Storage instances.
 */
public abstract class Command {

    /**
     * An unimplemented method for executing commands for the subclasses to implement how would
     * each unique command interact with the TaskList, Ui and Storage instances.
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     * @throws DukeException Describes the error encountered when executing the command
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    public abstract String execute(ListManager listManager, UI ui, StorageManager storageManager)
            throws DukeException, IOException;

    /**
     * Returns whether if the command when executed can exit the program.
     *
     * @return Whether the program can exit the program.
     */
    public boolean isExit() {
        return false;
    }
}

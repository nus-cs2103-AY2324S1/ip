package helpbuddy.command;

import java.io.IOException;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.storage.Storage;
import helpbuddy.task.TaskList;
import helpbuddy.ui.Ui;

/**
 * An abstract class representing a command. This class provides a basic structure for various commands.
 */
public abstract class Command {
    private boolean exit = false;

    /**
     * Executes the Command.
     * @param taskList the tasklist for Task to be added to.
     * @param ui the ui that prints message.
     * @param storage the storage with saved data in TaskList.
     * @throws IOException if unable to write data to file.
     * @throws HelpBuddyException if Command contains invalid input or not executable.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, HelpBuddyException;

    /**
     * Returns true if Command is ExitCommand; otherwise false.
     * @return true if Command is ExitCommand; otherwise false.
     */
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Updates the nature of Command if and only if it is an ExitCommand.
     */
    public void toggleExit() {
        this.exit = !this.exit;
    }
}

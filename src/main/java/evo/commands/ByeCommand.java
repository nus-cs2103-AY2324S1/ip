package evo.commands;

import evo.storage.Storage;
import evo.tasks.TaskList;
import evo.ui.Ui;

/**
 * The ByeCommand class represents a command to exit the application.
 * When executed, it displays a goodbye message to the user and exits the program.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand to handle the user's request to exit the application.
     */

    public ByeCommand() {
    }

    /**
     * Executes the ByeCommand to exit the application, displaying a goodbye message to the user.
     *
     * @param tasksList The TaskList containing the tasks.
     * @param ui The user interface for displaying messages to the user.
     * @param storage The storage component for interacting with task data storage.
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        ui.showExit();
    }
}

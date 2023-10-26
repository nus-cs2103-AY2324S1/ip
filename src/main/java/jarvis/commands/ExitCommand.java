package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;
import jarvis.ui.UiMessages;

/**
 * Represents the ExitCommand Class.
 * Responsible for handling exit operations.
 *
 * @author Shishir
 */
public class ExitCommand extends Command {

    /**
     * Executes the respective command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return UiMessages.FAREWELL;
    }

}

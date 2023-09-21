package qi.command;

import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represents the process of sending guidance to the users.
 */
public class HelpCommand extends Command {

    /**
     * Sends the format of every command available in the app
     * in the form of a string.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @return String notification created by Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGuidance();
    }
}

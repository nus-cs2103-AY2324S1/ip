package qi.command;

import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represent the execution when the input is invalid.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(false);
    }

    /**
     * Notifies users that the input is invalid and allows them to retry.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showInvalid();
    }
}

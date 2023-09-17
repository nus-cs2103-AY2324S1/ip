package jarvis.commands;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

/**
 * RemindCommand Class
 * Responsible for reminders on deadlines.
 *
 * @author Shishir
 */
public class RemindCommand extends Command {

    /**
     * Returns a string representation of all pending deadlines.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @return String representation of all pending deadlines.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getPendingTasks();
    }
}

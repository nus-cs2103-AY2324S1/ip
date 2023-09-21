package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The ByeCommand class represents the "bye" command in the Duke application.
 * It allows the user to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the "bye" command, displaying a goodbye message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving and loading tasks (not used in this command).
     * @return A string containing a goodbye message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }
}

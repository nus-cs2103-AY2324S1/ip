package duke.command;

import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

/**
 * Represents a command to exit the Duke chatbot application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying a goodbye message on the user interface.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return {@code true} to indicate that this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

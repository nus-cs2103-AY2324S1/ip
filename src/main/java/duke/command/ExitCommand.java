package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, shows the exit message on UI.
     *
     * @param tasks The list of tasks.
     * @param ui The UI for user interaction.
     * @param storage The storage for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Indicates that this command exits the application.
     *
     * @return true, as it is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

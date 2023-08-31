package duke.command;

import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to exit the application.
 * <p>
 * When executed, this command displays an exit message to the user and indicates to the application
 * that it should terminate.
 * </p>
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, displaying an exit message to the user.
     *
     * @param tasks List of tasks.
     * @param ui User interface.
     * @param storage Storage system.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Specifies that executing an ExitCommand will cause the application to exit.
     *
     * @return {@code true} indicating the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

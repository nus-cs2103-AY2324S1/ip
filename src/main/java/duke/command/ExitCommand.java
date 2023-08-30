package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command{
    private boolean isExit = true;

    /**
     * Executes the ExitCommand by displaying the program's goodbye message.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Checks if the ExitCommand should trigger the program to exit.
     *
     * @return True, as the exit command should trigger the program to exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

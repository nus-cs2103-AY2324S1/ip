package dialogix.command;

import dialogix.main.Storage;
import dialogix.main.TaskList;
import dialogix.main.Ui;

/**
 * Represents a command to exit the Dialogix application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by displaying an exit message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface.
     * @param storage The storage manager (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True, indicating that this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

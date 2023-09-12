package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a command to exit the chatBot.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand by printing an exit message to the user interface.
     *
     * @param tasks The list of tasks.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if this an exit command.
     */
    public boolean isExit() {
        return true;
    }
}

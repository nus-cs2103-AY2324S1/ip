package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

/**
 * Represents a command for handling unknown user inputs.
 */
public class UnknownCommand extends Command{
    private boolean isExit = false;

    /**
     * Executes the UnknownCommand by displaying a message indicating the input is not recognized.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Checks if the UnknownCommand should trigger the program to exit.
     *
     * @return False since an unknown command does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

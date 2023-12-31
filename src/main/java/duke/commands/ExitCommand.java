package duke.commands;

import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {
    private static final String EXIT_MESSAGE = "Bye. Have a productive day and see you soon!";

    /**
     * Executes the exit operation by adding an exit message to the UI.
     *
     * @param tasks   The list of tasks. (Not used in this context but present due to inheritance)
     * @param ui      The user interface used to display the exit message.
     * @param storage The storage used to save tasks. (Not used in this context but present due to inheritance)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addToResponse(EXIT_MESSAGE);
    }
}


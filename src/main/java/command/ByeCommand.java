package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;

/**
 * A command to exit the application.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a `ByeCommand` object.
     */
    public ByeCommand() {
    }

    /**
     * Executes the command to exit the application and displays a farewell message.
     *
     * @param task The task list (not used in this command).
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks (not used in this command).
     *
     * @return     A string representation of goodbye message.
     */
    @Override
    public String execute(TaskList task, Ui ui, FileHandler f) {
        return "Bye";
    }
}

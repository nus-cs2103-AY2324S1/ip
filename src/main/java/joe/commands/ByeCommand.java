package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand that marks the exit flag as true.
     */
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The TaskList on which the command should be executed.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage for saving and loading tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(BYE_MESSAGE);
        ui.exit();
        storage.saveToFile(tasks);
    }
}

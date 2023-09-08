package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a command.ExitCommand.
     */
    public ExitCommand() {
        super(null);
    }

    /**
     * Executes the command.ExitCommand, displaying a goodbye message and exiting the application.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        System.exit(0);
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return True, indicating that this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package commands;

import functions.*;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {}

    /**
     * Executes the ExitCommand to display an exit message.
     *
     * @param tasks   The TaskList (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage interface (not used in this command).
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMsg();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return Returns true indicating that this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

package harvard;

import javafx.application.Platform;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Returns true if the command is an exit command.
     *
     * @return True if the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Platform.exit();
        return ui.displayBye();
    }
}

package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to close the application.
 */
public class ExitCommand extends Command {
    /**
     * Constructs an ExitCommand.
     */
    public ExitCommand() {
        this.isExit = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showGoodbye();
    }
}

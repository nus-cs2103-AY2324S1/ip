package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `ByeCommand` class represents a command to exit the Duke application.
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ui.bye();
        this.exitsNext = true;
    }
}

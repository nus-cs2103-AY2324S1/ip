package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    private Exception e;
    public InvalidCommand(Exception e) {
        this.e = e;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.errorPrint(this.e);
    }
}

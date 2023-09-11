package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The InvalidCommand class represents a command that throws an error to users
 * when they provide an invalid user input as the command.
 */
public class InvalidCommand extends Command {
    private final DukeException ERROR;

    public InvalidCommand(DukeException error) {
        ERROR = error;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showErrorMessage(ERROR.getMessage());
    }
}

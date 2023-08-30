package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    private final DukeException error;

    public InvalidCommand(DukeException error) {
        this.error = error;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showErrorMessage(this.error.getMessage());
    }
}

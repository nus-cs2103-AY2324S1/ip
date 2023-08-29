package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    private String message;

    public IncorrectCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(this.message);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public String getMessage() {
        return this.message;
    }
}

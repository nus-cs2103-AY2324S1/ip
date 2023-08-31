package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ui.bye();
        this.willExitNext = true;
    }
}

package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        ui.showInvalidCommandMsg();
    }
}
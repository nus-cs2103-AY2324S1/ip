package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodByeMessage();
    }
}

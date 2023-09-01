package duke.commands;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.exceptions.DukeException;
import duke.utils.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
    };

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.print("Aw goodbye.. ಠ_ಠ");
    }
}

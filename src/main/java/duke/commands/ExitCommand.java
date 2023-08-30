package duke.commands;

import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public void execute(DukeList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package duke.commands;

import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.size());
        tasks.list();
        ui.showLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

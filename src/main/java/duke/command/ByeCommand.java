package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitResponse();
    };
    public boolean isExit() {
        return true;
    };
}

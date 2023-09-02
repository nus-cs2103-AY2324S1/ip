package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.prompt();
    }
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeGreeting();
    }

    @Override
    public boolean isExit() {
        return !super.isExit();
    }
}

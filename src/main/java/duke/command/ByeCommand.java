package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

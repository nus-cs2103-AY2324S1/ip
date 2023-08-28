package duke.command;

import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks.getAllTasks());
    }
}

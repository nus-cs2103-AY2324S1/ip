package duke.command;

import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showMessage(tasks.toString());
    }
}

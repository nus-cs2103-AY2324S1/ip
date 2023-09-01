package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        if (isRestoring) {
            return;
        }

        if (tasks.size() == 0) {
            ui.showNoTasks();
            return;
        }

        ui.showTasks(tasks.getAll(), false);
    }
}

package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents a ListCommand which should print all Tasks into standard output.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        if (isRestoring) {
            return "";
        }

        if (tasks.size() == 0) {
            return ui.showNoTasks();
        }

        return ui.showTasks(tasks.getAll(), false);
    }
}

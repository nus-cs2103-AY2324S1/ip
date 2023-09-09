package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class WrongCommand extends Command {

    /**
     * Marks the task as not completed (undoes completion).
     *
     * @return The task that was marked as not completed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showInvalidInput();
    }
}
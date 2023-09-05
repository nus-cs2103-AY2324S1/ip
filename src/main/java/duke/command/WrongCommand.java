package duke.command;

import duke.list.FunnyList;
import duke.storage.Storage;
import duke.ui.Ui;

public class WrongCommand extends Command {

    /**
     * Marks the task as not completed (undoes completion).
     *
     * @return The task that was marked as not completed.
     */
    @Override
    public void execute(FunnyList taskList, Ui ui, Storage storage) {
        ui.showInvalidInput();
    }
}
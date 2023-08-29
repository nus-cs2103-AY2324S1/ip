package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeStorageException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeAndGoodbyeMessage();
        try {
            storage.saveData(tasks);
        } catch (DukeStorageException e) {
            ui.showErrorMessage(e);
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

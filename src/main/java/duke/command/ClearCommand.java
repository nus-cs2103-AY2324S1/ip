package duke.command;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.ui.Ui;

public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        ui.showMessage("Got it. I've cleared all tasks.");
    }
}

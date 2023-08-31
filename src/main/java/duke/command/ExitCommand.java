package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.Storage;
import duke.DukeException;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}

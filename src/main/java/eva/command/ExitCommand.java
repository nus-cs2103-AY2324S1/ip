package eva.command;

import eva.Ui;
import eva.task.TaskList;
import eva.Storage;
import eva.DukeException;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(tasks);
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}

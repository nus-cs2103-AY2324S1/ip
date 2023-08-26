package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        storage.saveTasks(tasks);
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeFile(tasks);
        ui.exitMessage();
    };

    @Override
    public boolean isExit() {
        return true;
    };
}

package nobita.command;

import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.showAllTask();
    };

    @Override
    public boolean isExit() {
        return false;
    };
}

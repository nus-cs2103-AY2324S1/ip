package buddy;

import buddy.utils.Storage;
import buddy.utils.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.getAllTasks());
    }
}

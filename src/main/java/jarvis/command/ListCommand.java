package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (tasks.size() == 0) {
            ui.displayEmptyList();
        } else {
            ui.displayList(tasks);
        }
    }
}
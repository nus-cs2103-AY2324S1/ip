package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

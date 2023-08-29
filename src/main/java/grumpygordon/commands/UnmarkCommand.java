package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.*;
import grumpygordon.ui.*;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsUndone(this.index);
        ui.showCommandMessage("     Marked that task as undone!\n     "
                + tasks.getTask(this.index).toString() + "\n");
        storage.saveTasks(tasks);
    }
}

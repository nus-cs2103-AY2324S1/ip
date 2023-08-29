package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.*;
import grumpygordon.ui.*;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(this.index);
        ui.showCommandMessage("     Marked that task as done!\n     "
                + tasks.getTask(this.index).toString() + "\n");
        storage.saveTasks(tasks);
    }
}

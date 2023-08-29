package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.*;
import grumpygordon.ui.*;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCommandMessage("     grumpygordon.tasks.Task deleted from list!\n     "
                + tasks.getTask(this.index).toString() + "\n");
        tasks.deleteTask(this.index);
        storage.saveTasks(tasks);
    }
}

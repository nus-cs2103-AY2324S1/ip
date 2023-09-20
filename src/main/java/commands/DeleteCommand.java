package commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * DeleteCommand class for the command that asks to delete a task
 */
public class DeleteCommand extends Command {

    private int indexToDelete;

    public DeleteCommand(String index) {
        this.indexToDelete = java.lang.Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task removedTask = tasks.deleteTask(indexToDelete);
        storage.updateFileAfterDelete(indexToDelete + 1);
        return ui.printAfterDelete(tasks.getSize(), removedTask);
    }
}

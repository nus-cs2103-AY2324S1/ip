package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if index is invalid or the task exists.
        if (tasks.size() < index) {
            throw new DukeException("The task you are trying to delete either doesnt exist, or is already deleted!");
        }
        ui.showDelete(index, tasks.getTask(index - 1));
        tasks.delete(index - 1);
        storage.writeData(tasks.getAllTasks());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

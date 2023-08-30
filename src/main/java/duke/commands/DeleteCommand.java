package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() < index) {
            throw new DukeException("The task you are trying to delete is out of bounds!");
        }
        ui.acknowledgeDelete(index, tasks.getTask(index - 1));
        tasks.deleteTask(index);
        storage.updateStorage(tasks.getArrayList());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}

package commands;

import components.DukeException;
import components.Storage;
import components.Ui;
import tasks.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        return list.deleteTask(index, storage);
    }
}

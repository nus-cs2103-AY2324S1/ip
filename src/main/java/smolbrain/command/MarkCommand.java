package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

public class MarkCommand implements Command {

    private int id;
    private boolean loading;

    public MarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidRangeException {
        if (this.id < 0 || this.id >= tasks.getSize()) {
            throw new InvalidRangeException();
        }
        tasks.markTask(id);
        ui.showMessage("Nice! I've marked this task as done: \n" + tasks.getTask(id));
        tasks.updateTasks(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void setLoading() {
        this.loading = true;
    }

}

package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.TaskList;

public class UnmarkCommand implements Command {

    private int id;
    private boolean loading;

    public UnmarkCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidRangeException {
        if (this.id < 0 || this.id >= tasks.getSize()) {
            throw new InvalidRangeException();
        }
        tasks.unmarkTask(id);
        ui.showMessage("OK, I've marked this task as not done yet: \n" + tasks.getTask(id));
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

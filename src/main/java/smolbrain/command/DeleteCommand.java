package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.exception.InvalidRangeException;
import smolbrain.task.Task;
import smolbrain.task.TaskList;

public class DeleteCommand implements Command {

    private int id;
    private boolean loading;

    public DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidRangeException {
        if (this.id < 0 || this.id >= tasks.getSize()) {
            throw new InvalidRangeException();
        }
        Task task = tasks.getTask(id);
        tasks.deleteTask(id);
        ui.showMessage("Noted. I've removed this task: \n" + task);
        ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
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

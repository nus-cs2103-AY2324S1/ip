package smolbrain.command;

import smolbrain.Storage;
import smolbrain.Ui;
import smolbrain.task.Task;
import smolbrain.task.TaskList;

public class AddCommand implements Command {

    private Task task;
    private boolean loading;

    public AddCommand(Task task) {
        this.task = task;
    }

    public void setLoading() {
        this.loading = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        if (!loading) {
            ui.showMessage("Got it. I've added this task: \n" + task);
            ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
        }
        tasks.updateTasks(storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
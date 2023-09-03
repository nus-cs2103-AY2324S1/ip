package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;
import jo.task.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {

        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        tasks.addTask(this.task);
        storage.update(tasks);
        ui.modifyListResult(this.task, tasks, true);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}

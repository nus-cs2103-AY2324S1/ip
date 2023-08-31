package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.Task;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

public class AddCommand extends Command {

    private Task task;
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
        task.add(this.task);
        ui.showTaskAdded(task);
    }
}

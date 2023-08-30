package brotherman.commands;

import brotherman.storage.*;
import brotherman.tasks.*;
import brotherman.ui.*;

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

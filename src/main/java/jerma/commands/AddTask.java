package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class AddTask extends Command {
    private Task task;

    public AddTask(Ui ui, TaskList tasks, Task task) {
        super(ui, tasks);
        this.task = task;
    }

    public String execute() {
        tasks.add(task);
        return ui.newTask(task);
    }
}

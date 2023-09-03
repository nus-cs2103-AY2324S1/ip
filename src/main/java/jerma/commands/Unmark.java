package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Unmark extends Command {
    private Task task;

    public Unmark(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        this.task = this.tasks.get(index - 1);
    }

    public String execute() {
        this.task.setUndone();
        return this.ui.unmarkTask(this.task);
    }
}

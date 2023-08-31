package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Delete extends Command {
    private Task task;

    public Delete(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        this.task = tasks.get(index - 1);
    }

    public void execute() {
        this.tasks.remove(this.task);
        this.ui.deleteTask(this.task, tasks.size());
    }
}

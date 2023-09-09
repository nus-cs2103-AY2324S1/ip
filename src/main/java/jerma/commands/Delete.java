package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Delete extends Command {
    private Task task;

    public Delete(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        assert index > 0 || index <= tasks.size() : "Index out of range";
        this.task = tasks.get(index - 1);
    }

    public String execute() {
        this.tasks.remove(this.task);
        return this.ui.deleteTask(this.task, tasks.size());
    }
}

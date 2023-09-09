package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Delete extends Command {
    private Task task;

    public Delete(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        task = tasks.get(index - 1);
    }

    public String execute() {
        tasks.remove(task);
        return ui.deleteTask(task, tasks.size());
    }
}

package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Mark extends Command {
    private Task task;

    public Mark(Ui ui, TaskList tasks, int index) {
        super(ui, tasks);
        this.task = this.tasks.get(index - 1);
    }

    public String execute() {
        this.task.setDone();
        return this.ui.markTask(this.task);
    }
}

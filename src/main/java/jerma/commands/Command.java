package jerma.commands;

import jerma.utils.TaskList;
import jerma.utils.Ui;

public abstract class Command {
    protected Ui ui;
    protected TaskList tasks;

    protected Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    public abstract String execute();
}

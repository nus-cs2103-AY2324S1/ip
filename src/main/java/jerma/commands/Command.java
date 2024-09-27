package jerma.commands;

import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Command abstract class
 */
public abstract class Command {
    protected Ui ui;
    protected TaskList tasks;

    protected Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Executes the command
     *
     * @return Bot response
     */
    public abstract String execute();
}

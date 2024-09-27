package jerma.commands;

import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * List class, is a Command that prints the current tasklist
 */
public class List extends Command {
    public List(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    public String execute() {
        return ui.listTasks(this.tasks);
    }
}

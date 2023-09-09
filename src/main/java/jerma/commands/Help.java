package jerma.commands;

import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Help extends Command {
    public Help(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    public String execute() {
        return this.ui.help();
    }
}

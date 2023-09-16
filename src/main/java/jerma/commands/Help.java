package jerma.commands;

import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Help class, is a Command that returns a help message
 */
public class Help extends Command {
    public Help(Ui ui, TaskList tasks) {
        super(ui, tasks);
    }

    public String execute() {
        return ui.help();
    }
}

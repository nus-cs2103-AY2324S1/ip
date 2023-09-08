package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return Ui.exit();
    }
}

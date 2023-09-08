package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return Ui.listTasks(tasks);
    }
}

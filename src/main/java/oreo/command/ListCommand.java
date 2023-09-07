package oreo.command;

import oreo.task.TaskList;
import oreo.ui.Ui;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        return tasks.list();
    }
}

package oreo.command;

import oreo.task.TaskList;
import oreo.ui.Ui;

public class EmptyCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "uhhh what???";
    }
}

package oreo.command;

import oreo.task.TaskList;
import oreo.ui.Ui;

public class EmptyCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList tasks) {
        ui.say("uhhh what???");
    }
}

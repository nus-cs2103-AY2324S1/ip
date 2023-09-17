package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }
}

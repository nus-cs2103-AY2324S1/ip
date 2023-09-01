package command;

import duke.Storage;
import duke.Ui;
import task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        setExit(true);
    }
}

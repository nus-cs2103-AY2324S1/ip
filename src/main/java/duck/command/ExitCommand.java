package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;
import duck.task.TaskList;

public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui,Storage storage) throws DuckException{
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    };
}

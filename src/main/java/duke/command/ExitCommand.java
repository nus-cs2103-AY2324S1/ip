package duke.command;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

public class ExitCommand extends Command{
    private boolean isExit;

    public ExitCommand() {
        this.isExit = true;
    };

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.thank();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}

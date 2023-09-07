package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.bidGoodbye();
    }
}

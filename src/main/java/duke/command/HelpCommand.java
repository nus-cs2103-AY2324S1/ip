package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class HelpCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.showHelpList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to show the help dialog.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        return ui.showHelp();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

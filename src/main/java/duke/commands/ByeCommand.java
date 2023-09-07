package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The ByeCommand display a goodbye message when it is executed.
 */
public class ByeCommand extends Command {

    public ByeCommand() { }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

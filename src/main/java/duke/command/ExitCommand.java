package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ExitCommand class represents a command to quit the Duke application once done.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbyeMessage();
    }
}

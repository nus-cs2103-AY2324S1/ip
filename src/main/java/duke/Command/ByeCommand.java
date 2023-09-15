package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that handles the bye command.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return ui.printGoodByeMessage();
    }
}

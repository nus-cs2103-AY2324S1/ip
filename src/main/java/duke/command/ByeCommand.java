package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Executes the goodbye message.
 */
public class ByeCommand implements Command {

    /**
     * @inheritDoc
     */
    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        ui.buildMessage("Goodbye. Hope to be of service again soon!\n");
        return ui.sendMessage();
    }
}

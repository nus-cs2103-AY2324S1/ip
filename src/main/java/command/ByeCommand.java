package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to end the Chat Bot. Saves user input into file.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) {
        return ui.endBot();
    }

}

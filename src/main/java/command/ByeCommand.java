package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command to indicate that the user wishes to exit the chatbot.
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

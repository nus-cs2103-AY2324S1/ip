package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;

/**
 * Command to exit the chatbot
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printToScreen("Goodbye!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

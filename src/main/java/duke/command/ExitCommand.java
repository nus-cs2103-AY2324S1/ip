package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents the exit command to exit the chatbot.
 */
public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

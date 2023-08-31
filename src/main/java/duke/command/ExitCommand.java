package duke.command;

import duke.storage.Storage;
import duke.data.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the chatbot application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }
}

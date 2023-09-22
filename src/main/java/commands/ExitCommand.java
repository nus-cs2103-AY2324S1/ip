package commands;

import data.TaskList;
import storage.Storage;
import ui.UiExitMessage;
import ui.UiMessage;

/**
 * The ExitCommand class.
 * Handles the exit logic when the user enters the exit command.
 */
public class ExitCommand extends Command {
    
    @Override
    public UiMessage execute(
            TaskList tasks, Storage storage) {
        return new UiExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

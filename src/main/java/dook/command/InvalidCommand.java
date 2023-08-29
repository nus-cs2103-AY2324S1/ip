package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;


/**
 * Represents any invalid command.
 */
public class InvalidCommand extends Command {
    /**
     * Displays a list of all possible commands and their descriptions.
     *
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.displayHelp();
    }
}

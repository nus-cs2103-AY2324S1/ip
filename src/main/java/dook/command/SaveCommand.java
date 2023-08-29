package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

/**
 * Command for saving the current task list.
 */
public class SaveCommand extends Command {
    /**
     * Saves the given task list to file via the given storage.
     * Displays a confirmation message to the user.
     *
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(storage.save(taskList));
    }

}

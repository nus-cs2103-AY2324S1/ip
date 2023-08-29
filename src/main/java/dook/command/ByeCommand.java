package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

/**
 * Command for exiting the program.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        this.isExit = true;
    }
    /**
     * Saves the given task list to file via the given storage.
     * Displays a farewell message to the user.
     *
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        try {
            storage.save(taskList);
        } catch (DookException e) {
            uiDisplay.printMessage(e.getMessage());
        } finally {
            uiDisplay.bidFarewell();
        }
    }

}

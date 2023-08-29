package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;


/**
 * Command for marking a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;
    public UnmarkCommand(int index) {
        this.index = index;

    }
    /**
     * Marks a task at the stored index in the task list as not done.
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(taskList.markTask(index, false));
    }
}

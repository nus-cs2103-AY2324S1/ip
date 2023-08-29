package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

/**
 * Command for deleting a task.
 */
public class DeleteTaskCommand extends Command {
    private final int index;
    public DeleteTaskCommand(int index) {
        this.index = index;
    }
    /**
     * Deletes the task at the stored index in the given task list.
     * Displays a confirmation message notifying the user which task they just deleted.
     *
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(taskList.deleteTask(index));
    }
}

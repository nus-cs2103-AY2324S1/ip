package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;
import dook.services.UiDisplay;

/**
 * Command to search for tasks matching a certain keyword
 */
public class FindCommand extends Command {
    private final String query;
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Checks the task list for any tasks matching the stored query.
     * Displays the result to the user.
     * @param storage Given storage.
     * @param uiDisplay Given UI display.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException {
        uiDisplay.printMessage(taskList.filterTasks((task) -> task.descriptionContains(query)));
    }
}

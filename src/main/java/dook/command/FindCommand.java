package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;

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
     * @param taskList Given task list.
     * @return  Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DookException {
        return taskList.filterTasks((task) -> task.descriptionContains(query));
    }
}

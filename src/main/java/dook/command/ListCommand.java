package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;


/**
 * Command for displaying all current tasks.
 */
public class ListCommand extends Command {
    /**
     * Displays a list of all tasks in the given task list.
     * @param storage Given storage.
     * @param taskList Given task list.
     * @return  Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DookException {
        return taskList.toString();
    }

}

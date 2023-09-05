package dook.command;

import dook.DookException;
import dook.services.Storage;
import dook.services.TaskList;


/**
 * Command for marking a task as done.
 */
public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task at the stored index in the task list as done.
     * @param storage Given storage.
     * @param taskList Given task list.
     * @throws DookException Exception thrown by Dook.
     * @return  Message to be displayed in GUI.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DookException {
        return taskList.markTask(index, true);
    }
}

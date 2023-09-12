package dook.command;

import dook.DookException;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;

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
     * @param storage  Given storage.
     * @param taskList Given task list.
     * @param parser
     * @return Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Parser parser) throws DookException {
        return taskList.deleteTask(index);
    }
}

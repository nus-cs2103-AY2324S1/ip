package dook.command;

import dook.DookException;
import dook.services.Parser;
import dook.services.Storage;
import dook.services.TaskList;


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
     *
     * @param storage  Given storage.
     * @param taskList Given task list.
     * @param parser
     * @return Message to be displayed in GUI.
     * @throws DookException Exception thrown by Dook.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Parser parser) throws DookException {
        return taskList.markTask(index, false);
    }
}

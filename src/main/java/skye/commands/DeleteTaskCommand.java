package skye.commands;

import java.io.IOException;

import skye.data.TaskList;
import skye.data.VenueList;
import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the command for deleting tasks.
 */
public class DeleteTaskCommand extends DeleteCommand {

    public static final String RESOURCE = "task";

    /**
     * Instantiates the delete command for deleting tasks
     *
     * @param taskNumber Index number on the task list
     */
    public DeleteTaskCommand(int taskNumber) {
        super(taskNumber);
    }

    /**
     * Executes the delete task command
     *
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     * @throws DukeException Describes the error encountered when executing the command
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    @Override
    public String execute(TaskList taskList, VenueList venueList, UI ui, Storage storage)
            throws DukeException, IOException {
        Task removedTask = taskList.deleteTask(getIndex());
        storage.write(taskList.getTasks());
        return ui.showRemovedTask(removedTask, taskList.getTasks());
    }
}

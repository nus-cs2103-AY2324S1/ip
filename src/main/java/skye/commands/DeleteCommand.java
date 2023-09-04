package skye.commands;

import java.io.IOException;

import skye.data.TaskList;
import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents the command for deleting tasks
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int taskNumber;

    /**
     * Instantiates the delete command for deleting tasks
     *
     * @param taskNumber Index number on the task list
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
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
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        Task removedTask = taskList.deleteTask(taskNumber);
        storage.write(taskList.getTasks());
        ui.showRemovedTask(removedTask, taskList.getTasks());
    }
}

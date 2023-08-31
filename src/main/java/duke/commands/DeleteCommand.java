package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

/**
 * Represents the command for deleting tasks
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private final int taskNumber;

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

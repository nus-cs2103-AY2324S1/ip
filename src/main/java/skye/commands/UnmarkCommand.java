package skye.commands;

import skye.data.TaskList;
import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.storage.Storage;
import skye.ui.UI;

import java.io.IOException;

/**
 * Represents the command to mark a completed task as incomplete
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark task command by marking an already completed task on the TaskList as
     * incomplete and updating the save file on the specified save directory and showing the
     * task that was marked as incomplete on the UI.
     *
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     * @throws DukeException Describes the error encountered when executing the command
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        Task unmarkedTask = taskList.unmarkTask(this.taskNumber);
        storage.write(taskList.getTasks());
        ui.showUnmarkedTask(unmarkedTask);
    }
}
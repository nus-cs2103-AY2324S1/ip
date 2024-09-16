package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.TaskList;
import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the command to mark a completed task as incomplete.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private final int taskNumber;

    /**
     * Instantiates the unmark command to mark a completed task as incomplete.
     *
     * @param taskNumber Index number on the task list
     */
    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the unmark task command by marking an already completed task on the TaskList as
     * incomplete and updating the save file on the specified save directory and showing the
     * task that was marked as incomplete on the UI.
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     * @throws DukeException Describes the error encountered when executing the command
     * @throws IOException Describes the I/O error encountered in the OS file system
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager)
            throws DukeException, IOException {
        TaskList taskList = listManager.getTaskList();
        Task unmarkedTask = taskList.unmarkTask(this.taskNumber);
        assert !unmarkedTask.isDone();
        storageManager.writeTasks(taskList.getTasks());
        return ui.showUnmarkedTask(unmarkedTask);
    }
}

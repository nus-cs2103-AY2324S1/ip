package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.TaskList;
import skye.data.exception.DukeException;
import skye.data.task.Task;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the command to mark a task as complete.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private final int taskNumber;

    /**
     * Instantiates the mark command which is used to mark a task as complete
     *
     * @param taskNumber Index number on the task list
     */
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark task command by marking the task on the TaskList as complete and
     * updating the save file on the specified save directory and showing the task that
     * was marked as completed on the UI.
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
        Task markedTask = taskList.markTask(taskNumber);
        assert markedTask.isDone();
        storageManager.writeTasks(taskList.getTasks());
        return ui.showMarkedTask(markedTask);
    }
}

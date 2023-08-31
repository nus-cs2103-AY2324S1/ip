package duke.commands;

import java.io.IOException;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
public class DeleteTaskCommand extends Command {

    /** ID of task to be deleted. */
    private final int taskID;
    /**
     * Constructor to initialize DeleteTaskCommand.
     *
     * @param taskID ID of task to be deleted.
     */
    public DeleteTaskCommand(int taskID) {
        this.taskID = taskID;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Task deletedTask = taskList.deleteTask(taskID);
        ui.showTaskDeleted(deletedTask, taskList.countTasks());
        Storage.save(taskList);
    }
}

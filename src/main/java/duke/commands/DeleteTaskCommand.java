package duke.commands;

import java.io.IOException;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.exception.DukeException;
import duke.data.task.Task;
public class DeleteTaskCommand extends Command {

    private final int taskID;

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

import java.io.IOException;

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

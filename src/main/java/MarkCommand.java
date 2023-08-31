import java.io.IOException;

public class MarkCommand extends Command {

    private final int taskID;

    public MarkCommand(int taskID) throws DukeException {
        if (taskID < 1)
            throw new DukeException("☹ OOPS!!! Invalid Task ID.");
        this.taskID = taskID;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        taskList.markTask(taskList.getTask(taskID - 1));
        ui.showMarked(taskList.getTask(taskID - 1));
        Storage.save(taskList);
    }
}

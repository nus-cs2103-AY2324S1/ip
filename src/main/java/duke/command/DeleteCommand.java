package duke.command;
import duke.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveTasks(taskList);
        return ui.deleteTaskMessage(task, taskList.numOfTasks());
    }
}

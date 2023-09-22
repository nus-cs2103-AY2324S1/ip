package duke.command;
import duke.Task;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class TaskCommand extends Command {
    private Task task;
    public TaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addTask(task);
        storage.saveTasks(taskList);
        return ui.addTaskMessage(task, taskList.numOfTasks());
    }
}

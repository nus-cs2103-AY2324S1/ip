import java.io.IOException;

public class AddCommand extends Command {

    public Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.showTaskAdded(taskList);
    }
}
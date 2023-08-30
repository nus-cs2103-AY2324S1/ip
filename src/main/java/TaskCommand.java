import java.util.ArrayList;

public class TaskCommand extends Command {
    private final Task newTask;

    public TaskCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        storage.saveTasks(tasks);
    }
}

import java.util.ArrayList;

public class TodoCommand extends Command {
    private final Task newTask;

    public TodoCommand(Task newTask) {
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

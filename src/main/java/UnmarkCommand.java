import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        Task currentTask = tasks.get(index - 1);
        currentTask.markAsUndone();
        ui.showTaskUndone(currentTask);
        storage.saveTasks(tasks);
    }
}

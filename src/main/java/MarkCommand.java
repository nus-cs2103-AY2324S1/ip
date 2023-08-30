import java.util.ArrayList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        Task currentTask = tasks.get(index - 1);
        currentTask.markAsDone();
        ui.showTaskDone(currentTask);
        storage.saveTasks(tasks);
    }
}

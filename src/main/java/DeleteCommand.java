import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || index > tasks.size()) {
            throw new DukeException(Ui.LINE + Messages.INVALID_INDEX_MESSAGE + Ui.LINE);
        }

        Task removed = tasks.remove(index - 1);
        ui.showTaskDeleted(removed, tasks.size());
        storage.saveTasks(tasks);
    }
}

import java.io.IOException;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(Task task) {
        super(task);
    }

    public DeleteCommand(int index) {
        super(null);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.getSize() || index < 0) throw new InvalidTaskNumberException();
        Task task = tasks.getTaskAtIndex(this.index);
        tasks.remove(task);
        storage.update(tasks.getTasks());
        ui.showDeleteSuccess(task.toString());
    }
}

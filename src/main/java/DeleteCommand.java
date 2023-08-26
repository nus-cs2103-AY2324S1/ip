import java.io.IOException;

public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        Task t = tasks.delete(index);
        storage.rewrite(tasks);
        ui.showDeleteMessage(t, tasks.getSize());
    }
}

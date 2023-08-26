import java.io.IOException;

public class AddCommand extends Command{
    Task task;
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        tasks.add(this.task);
        storage.append(this.task);
        ui.showAddMessage(task, tasks.getSize());
    }
}

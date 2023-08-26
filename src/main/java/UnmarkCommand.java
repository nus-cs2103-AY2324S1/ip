import java.io.IOException;

public class UnmarkCommand extends Command {
    int index;

    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        tasks.unmark(index);
        storage.rewrite(tasks);
        ui.showUnmarkMessage();
    }
}

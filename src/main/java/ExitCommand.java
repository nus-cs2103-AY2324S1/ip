import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand(Task task) {
        super(task);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showParting();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

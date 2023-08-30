import java.io.File;
import java.io.PrintWriter;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks,Ui ui, Storage storage) {
        storage.saveToFile(tasks.list());
        ui.showGoodbyMessage();
    }
}

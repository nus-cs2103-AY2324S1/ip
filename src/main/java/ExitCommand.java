import java.io.IOException;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showGoodbye();
    }
}

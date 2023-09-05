import java.io.IOException;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super(false);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showInvalid();
    }
}

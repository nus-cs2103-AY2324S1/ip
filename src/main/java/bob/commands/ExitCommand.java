package bob.commands;
import bob.*;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}

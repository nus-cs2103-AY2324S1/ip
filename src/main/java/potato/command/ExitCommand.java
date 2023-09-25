package potato.command;

import javafx.application.Platform;
import potato.*;

public class ExitCommand extends Command {
    public ExitCommand() {
        super.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        // Platform.exit();
        return ui.showGoodbye();
    }
}

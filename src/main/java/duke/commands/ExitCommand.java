package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    public void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

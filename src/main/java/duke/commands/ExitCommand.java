package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents an ExitCommand where the program should quit.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    @Override
    public String execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        return ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

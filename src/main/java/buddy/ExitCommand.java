package buddy;

import buddy.utils.Storage;
import buddy.utils.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
    }
}

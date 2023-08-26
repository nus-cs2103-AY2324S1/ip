package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class UnknownCommand extends Command {

    public UnknownCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showUnknownCommand();
    }
}

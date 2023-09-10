package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return ui.printGoodByeMessage();
    }
}

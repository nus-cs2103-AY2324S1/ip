package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

public class UnknownCommand extends Command{
    private boolean isExit = false;
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}

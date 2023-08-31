package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public abstract class Command {
    private boolean exit;

    public Command(boolean exit) {
        this.exit = exit;
    }
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
    public boolean isExit() {
        return this.exit;
    }
}

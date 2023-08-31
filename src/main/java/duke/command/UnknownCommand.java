package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printCommandNotFound();
    }
}

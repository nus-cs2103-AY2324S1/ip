package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        storage.save(list);
        ui.printExitMessage();
    }
}

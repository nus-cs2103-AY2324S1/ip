package duke.commands;

import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        storage.writeFile(tasks.retrieveForStorage());
        ui.showOutro();
    }
}

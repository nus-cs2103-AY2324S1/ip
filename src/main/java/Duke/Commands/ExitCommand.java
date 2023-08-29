package Duke.Commands;

import Duke.Tools.Storage;
import Duke.Tools.TaskList;
import Duke.Tools.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        this.isExit = true;
        storage.writeFile(tasks.retrieveForStorage());
        ui.showOutro();
    }
}

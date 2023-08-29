package duke;

import duke.Command;

public class ExitTask extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeScanner();
        ui.exitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

package duke;

import duke.Command;

public class ListTask extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

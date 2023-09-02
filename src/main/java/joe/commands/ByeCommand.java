package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;

public class ByeCommand extends Command {
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    public ByeCommand() {
        this.isExit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(BYE_MESSAGE);
        ui.exit();
        storage.saveToFile(tasks);
    }
}

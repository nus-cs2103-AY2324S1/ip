package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

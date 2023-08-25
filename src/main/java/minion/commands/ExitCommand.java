package minion.commands;

import minion.storage.Storage;
import minion.data.TaskList;
import minion.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.tearDown();
    }
}

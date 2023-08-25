package minion.commands;

import minion.storage.Storage;
import minion.data.TaskList;
import minion.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }
}

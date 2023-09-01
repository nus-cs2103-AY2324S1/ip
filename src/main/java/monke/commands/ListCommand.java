package monke.commands;

import monke.Storage;
import monke.TaskList;
import monke.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.displayList(tasks);
    }
}

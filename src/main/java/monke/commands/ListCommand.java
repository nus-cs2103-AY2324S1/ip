package monke.commands;

import monke.*;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.displayList(tasks);
    }
}

package joe.commands;

import joe.Storage;
import joe.TaskList;
import joe.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }
}

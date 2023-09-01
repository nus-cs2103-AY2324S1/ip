package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;

public class ListCommand extends Command {


    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}

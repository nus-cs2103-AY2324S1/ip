package jo.command;

import jo.JoException;
import jo.Storage;
import jo.TaskList;
import jo.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JoException {
        ui.printList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        return ui.showExit();
    }
}

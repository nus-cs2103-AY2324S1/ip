package qi.command;

import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

public class HelpCommand extends Command {
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showGuidance();
    }
}

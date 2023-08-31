package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandDetailException {
        ui.showList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

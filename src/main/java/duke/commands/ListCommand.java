package duke.commands;

import duke.exceptions.CommandDetailException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a list command in the Duke application.
 */
public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandDetailException {
        ui.showList(tasks);
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) throws CommandDetailException {
        return ui.showListGui(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

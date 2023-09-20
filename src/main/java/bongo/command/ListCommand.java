package bongo.command;

import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.TaskList;

/**
 * A class for a ListCommand.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showAllTasks(tasks.getAllTasks());
    }
}

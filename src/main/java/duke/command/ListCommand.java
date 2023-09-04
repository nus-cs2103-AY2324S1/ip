package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list and display all tasks in Duke's task list.
 */
public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showListMessage(taskList.find(""));
    }
}

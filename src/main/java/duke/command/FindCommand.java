package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {
    protected String searchString;
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        return taskList.getSearchTask(this.searchString);
    }
}

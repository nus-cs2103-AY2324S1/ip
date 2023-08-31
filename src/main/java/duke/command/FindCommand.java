package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> result = items.searchFor(keyword);
        ui.listSearch(result);
    }
}

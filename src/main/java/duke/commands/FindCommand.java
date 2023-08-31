package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.tasks.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.filterByKeyword(this.keyword);
        ui.acknowledgeFind(filteredTasks);
        storage.updateStorage(tasks.getArrayList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

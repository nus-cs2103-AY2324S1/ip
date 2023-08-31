package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> resultList = list.findTask(this.keyword);
        ui.showListMatching(resultList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

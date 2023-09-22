package duke.command;


import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * A Find Command that when executed will find tasks in the data file that at least partially matches the keyword
 */
public class FindCommand extends Command {
    private String keyword;
    private static final int LIMIT = 2;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        ArrayList<Task> tasks;
        tasks = taskList.filterTaskByKeyword(this.keyword, LIMIT);
        ui.showFilteredTasksList(tasks);
    }
}

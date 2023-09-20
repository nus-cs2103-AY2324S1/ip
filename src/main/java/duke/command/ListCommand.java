package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    private String queryParam;

    public ListCommand(String queryParam) {
        this.queryParam = queryParam;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.showAllTasks(ui, queryParam);
    }
}

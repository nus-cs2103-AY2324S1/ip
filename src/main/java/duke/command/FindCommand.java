package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 *  The FindCommand class represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword Keyword to be searched from the list of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task: taskList.getList()) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.showMatchingTasksToKeyword(matchingTasks);
    }
}

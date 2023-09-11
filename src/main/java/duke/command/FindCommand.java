package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 *  The FindCommand class represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {
    private final String KEYWORD;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword Keyword to be searched from the list of tasks.
     */
    public FindCommand(String keyword) {
        KEYWORD = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task: taskList.getList()) {
            if (task.toString().contains(KEYWORD)) {
                matchingTasks.add(task);
            }
        }
        return ui.showMatchingTasksToKeyword(matchingTasks);
    }
}

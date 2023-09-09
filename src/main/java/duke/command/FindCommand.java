package duke.command;

import java.util.function.Predicate;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the provided keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        Predicate<Task> containsKeyword = task -> task.getDescription().contains(keyword);
        TaskList matchingTasks = taskList.filter(containsKeyword);

        return ui.showMatchingTasks(matchingTasks);
    }
}

package duke.command;

import java.util.ArrayList;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        ui.showMatchingTasks(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

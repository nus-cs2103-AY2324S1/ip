package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The FindTaskCommand class.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Constructor for the FindTaskCommand class.
     * @param keyword The string that is contained in the name of the task to be found.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns a list of tasks with names that contain the keyword.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.findTasks(taskList, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

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

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.findTasks(taskList, keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

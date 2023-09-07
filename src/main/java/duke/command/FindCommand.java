package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        return uiManager.getMatchingList(taskList.findTasks(keyword));
    }
}

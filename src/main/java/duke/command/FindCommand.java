package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;

/**
 * This is a command to find  task in the task list.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs the find command.
     *
     * @param keyword The keyword to find.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        return uiManager.getMatchingList(taskList.findTasks(keyword));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof FindCommand) {
            FindCommand temp = (FindCommand) obj;
            if (this.keyword.equals(temp.keyword)) {
                return true;
            }
        }
        return false;
    }
}

package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        return uiManager.getListMessage(taskList);
    }

}

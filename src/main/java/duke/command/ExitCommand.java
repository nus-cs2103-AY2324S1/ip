package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
import javafx.application.Platform;

public class ExitCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        Platform.exit();
        return null;
    }
}

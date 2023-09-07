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
        Platform.exit();
        return null;
    }
}

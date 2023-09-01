package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;
import javafx.application.Platform;

/**
 * Represents an exit command by the user.
 */
public class ByeCommand implements Command {

    private static final String commandString = "bye";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        Platform.exit();
    }
}

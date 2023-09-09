package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.application.Platform;

public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Platform.exit();
        return ui.showGoodbye();
    }
}

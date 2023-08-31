package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.UI;

public class ByeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
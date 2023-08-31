package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ui.showHelpMessage(storage.loadHelpGuide());
    }
}
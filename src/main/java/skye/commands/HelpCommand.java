package skye.commands;

import skye.data.TaskList;
import skye.storage.Storage;
import skye.ui.UI;

import java.io.IOException;

/**
 * Represents a command to show the program usage to help the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        ui.showHelpMessage(storage.loadHelpGuide());
    }
}
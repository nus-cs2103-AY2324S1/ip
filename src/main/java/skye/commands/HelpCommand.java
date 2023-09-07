package skye.commands;

import java.io.IOException;

import skye.data.TaskList;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents a command to show the program usage to help the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws IOException {
        return ui.showHelpMessage(storage.loadHelpGuide());
    }
}

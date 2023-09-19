package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents a command to show the program usage to help the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager)
            throws IOException {
        return ui.showHelpMessage(storageManager.loadHelpGuide());
    }
}

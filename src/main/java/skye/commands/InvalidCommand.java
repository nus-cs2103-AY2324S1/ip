package skye.commands;

import skye.data.ListManager;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents an invalid command that the user has entered.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command in which this case a help message
     * is shown by the UI.
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) {
        return ui.showInvalidCommandMsg();
    }
}

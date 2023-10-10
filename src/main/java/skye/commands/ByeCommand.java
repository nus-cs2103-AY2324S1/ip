package skye.commands;

import skye.data.ListManager;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the command to exit from the program.
 */
public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes the ByeCommand
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) {
        return ui.showGoodBye();
    }

    /**
     * Exits the program when called.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

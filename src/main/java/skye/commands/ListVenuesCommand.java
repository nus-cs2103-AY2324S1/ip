package skye.commands;

import skye.data.ListManager;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents the command to list all venues.
 */
public class ListVenuesCommand extends ListCommand {

    public static final String RESOURCE = "venues";

    /**
     * Executes the list command by retrieving a list of venues from the
     * VenueList and displaying it on the UI.
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) {
        return ui.showVenues(listManager.getVenueList().getVenues());
    }
}

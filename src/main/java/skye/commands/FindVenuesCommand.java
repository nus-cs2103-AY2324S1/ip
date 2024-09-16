package skye.commands;

import java.util.List;

import skye.data.ListManager;
import skye.data.VenueList;
import skye.data.venue.Venue;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents a command to find venues containing a keyword specified by the user.
 */
public class FindVenuesCommand extends FindCommand {
    public static final String RESOURCE = "venues";

    public FindVenuesCommand(String keyword) {
        super(keyword);
    }

    /**
     * Execute the find tasks command and returns a list of tasks containing the keyword in
     * the description.
     *
     * @param listManager ListManager
     * @param ui UI
     * @param storageManager StorageManager
     */
    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager) {
        VenueList venueList = listManager.getVenueList();
        List<Venue> venues = venueList.findVenuesContaining(getKeyword());
        return ui.showFoundVenues(venues);
    }
}

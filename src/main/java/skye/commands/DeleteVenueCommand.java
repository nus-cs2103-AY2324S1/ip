package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.VenueList;
import skye.data.exception.DukeException;
import skye.data.venue.Venue;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents a command to delete a venue.
 */
public class DeleteVenueCommand extends DeleteCommand {
    public static final String RESOURCE = "venues";

    /**
     * Instantiates the delete command for deleting a venue.
     *
     * @param venueNumber Venue number on the list
     */
    public DeleteVenueCommand(int venueNumber) {
        super(venueNumber);
    }

    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager)
            throws DukeException, IOException {
        VenueList venueList = listManager.getVenueList();
        Venue removedVenue = venueList.deleteVenue(getIndex());
        storageManager.writeVenues(venueList.getVenues());
        return ui.showRemovedVenue(removedVenue, venueList.getVenues());
    }
}

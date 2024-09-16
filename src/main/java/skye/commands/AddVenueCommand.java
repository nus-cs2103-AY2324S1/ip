package skye.commands;

import java.io.IOException;

import skye.data.ListManager;
import skye.data.VenueList;
import skye.data.exception.DukeException;
import skye.data.venue.Venue;
import skye.storage.StorageManager;
import skye.ui.UI;

/**
 * Represents a command for adding venues.
 */
public class AddVenueCommand extends Command {

    public static final String COMMAND_WORD = "venue";

    private final Venue venue;

    public AddVenueCommand(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String execute(ListManager listManager, UI ui, StorageManager storageManager)
            throws DukeException, IOException {
        VenueList venueList = listManager.getVenueList();
        venueList.addVenue(venue);
        storageManager.writeVenues(venueList.getVenues());
        return ui.showAddedVenue(venue, venueList.getVenues());
    }
}

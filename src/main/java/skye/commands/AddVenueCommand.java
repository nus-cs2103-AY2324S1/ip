package skye.commands;

import java.io.IOException;

import skye.data.TaskList;
import skye.data.VenueList;
import skye.data.exception.DukeException;
import skye.data.venue.Venue;
import skye.storage.Storage;
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
    public String execute(TaskList taskList, VenueList venueList, UI ui, Storage storage)
            throws DukeException, IOException {
        venueList.addVenue(venue);
        storage.writeVenue(venueList.getVenues());
        return ui.showAddedVenue(venue, venueList.getVenues());
    }
}

package skye.commands;

import java.io.IOException;

import skye.data.TaskList;
import skye.data.VenueList;
import skye.data.exception.DukeException;
import skye.data.venue.Venue;
import skye.storage.Storage;
import skye.ui.UI;

/**
 * Represents a command to delete a venue.
 */
public class DeleteVenueCommand extends DeleteCommand {
    public static final String RESOURCE = "venue";

    /**
     * Instantiates the delete command for deleting a venue.
     *
     * @param venueNumber Venue number on the list
     */
    public DeleteVenueCommand(int venueNumber) {
        super(venueNumber);
    }

    @Override
    public String execute(TaskList taskList, VenueList venueList, UI ui, Storage storage)
            throws DukeException, IOException {
        Venue removedVenue = venueList.deleteVenue(getIndex());
        storage.writeVenue(venueList.getVenues());
        return ui.showRemovedVenue(removedVenue, venueList.getVenues());
    }
}

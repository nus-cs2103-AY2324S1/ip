package skye.commands;

import java.util.List;

import skye.data.TaskList;
import skye.data.VenueList;
import skye.data.venue.Venue;
import skye.storage.Storage;
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
     * @param taskList TaskList
     * @param ui UI
     * @param storage Storage
     */
    @Override
    public String execute(TaskList taskList, VenueList venueList, UI ui, Storage storage) {
        List<Venue> venues = venueList.findVenuesContaining(getKeyword());
        return ui.showFoundVenues(venues);
    }
}

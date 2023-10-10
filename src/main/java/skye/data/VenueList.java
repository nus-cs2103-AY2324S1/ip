package skye.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.venue.Venue;

/**
 * Represents a container storing a list of venues that allows
 * venue related operations such as insertion, deletion and updating
 * of venue completion status to be performed.
 */
public class VenueList {

    /** A list to store venue objects  */
    private final List<Venue> venues;

    public VenueList() {
        this.venues = new ArrayList<>();
    }

    public VenueList(List<Venue> venues) {
        this.venues = venues;
    }

    /**
     * Returns a list of venues that is currently stored in the program.
     *
     * @return a list of venues
     */
    public List<Venue> getVenues() {
        return venues;
    }

    /**
     * Adds a new venue to the list.
     *
     * @param venue The new value to be added to the list
     */
    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    /**
     * Checks if a venue number is within the valid range.
     *
     * @param venueNumber The numerical order of the venue in the list.
     * @return true if 1 < venueNumber < number of venues
     *         false otherwise
     */
    private boolean isInvalidVenueNumber(int venueNumber) {
        return (venueNumber <= 0) || (venueNumber > venues.size());
    }

    /**
     * Delete a venue
     *
     * @param venueNumber The numerical order of the venue in the list.
     * @return The venue that was deleted
     * @throws DukeException if venueNumber < 1 or venueNumber > number of venues
     */
    public Venue deleteVenue(int venueNumber) throws DukeException {
        if (isInvalidVenueNumber(venueNumber)) {
            throw new DukeException(DukeExceptionType.INVALID_RANGE);
        } else {
            return venues.remove(venueNumber - 1);
        }
    }

    /**
     * Finds a list of venues whose names contain a specified keyword.
     *
     * @param keyword Keyword specified by the user
     * @return A list of matching venues
     */
    public List<Venue> findVenuesContaining(String keyword) {
        return venues.stream()
                .filter(venue -> venue.getName().contains(keyword))
                .collect(Collectors.toList());
    }
}

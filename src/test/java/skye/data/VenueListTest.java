package skye.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import skye.data.exception.DukeException;
import skye.data.venue.Venue;

public class VenueListTest {
    private VenueList venueList;

    @BeforeEach
    public void init() {
        venueList = new VenueList();
        venueList.addVenue(new Venue("SR1", "Computing Drive", 300, 15.99));
        venueList.addVenue(new Venue("LT11", "Kent Ridge Dr", 300, 25.99));
        venueList.addVenue(new Venue("LT27", "Lower Kent Ridge Rd", 400, 20.99));
        venueList.addVenue(new Venue("MPSH1", "Sports Drive", 1000, 49.99));
    }

    @Test
    public void addVenue_validVenue_success() {
        int expectedSize = venueList.getVenues().size() + 1;
        venueList.addVenue(new Venue("UT-AUD1", "College Ave", 300, 15.99));
        int actualSize = venueList.getVenues().size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void deleteVenue_validIndex_success() {
        int expectedSize = venueList.getVenues().size() - 1;
        try {
            venueList.deleteVenue(3);
            int actualSize = venueList.getVenues().size();
            assertEquals(expectedSize, actualSize);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void findVenuesContaining_existingKeyword_matchingVenues() {
        List<Venue> expected = new ArrayList<>();
        expected.add(new Venue("LT11", "Kent Ridge Dr", 300, 25.99));
        expected.add(new Venue("LT27", "Lower Kent Ridge Rd", 400, 20.99));

        List<Venue> actual = venueList.findVenuesContaining("LT");

        assertEquals(expected, actual);
    }

    @Test
    public void findVenuesContaining_newKeyword_noVenuesFound() {
        List<Venue> expected = new ArrayList<>();

        List<Venue> actual = venueList.findVenuesContaining("room");

        assertEquals(expected, actual);
    }

}

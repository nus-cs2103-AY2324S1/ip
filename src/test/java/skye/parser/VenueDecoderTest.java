package skye.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.venue.Venue;

public class VenueDecoderTest {

    private VenueDecoder venueDecoder;

    @BeforeEach
    public void init() {
        venueDecoder = new VenueDecoder();
    }

    @Test
    public void decode_venue_success() {
        String encodedLine = "V | ICube Auditorium | 21 Heng Mui Keng Terrace | 300 | 15.99";
        Venue expected = new Venue("ICube Auditorium", "21 Heng Mui Keng Terrace", 300, 15.99);
        try {
            Venue actual = venueDecoder.decode(encodedLine);
            assertEquals(expected, actual);
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void decode_venueWithLessParameters_throwsException() {
        String encodedLine = "V | LT13 | Kent Ridge Dr | 300";
        Exception exception = assertThrows(DukeException.class, () -> venueDecoder.decode(encodedLine));
        assertEquals(DukeExceptionType.INVALID_VENUE_SAVE_FORMAT.getMessage(), exception.getMessage());
    }

}

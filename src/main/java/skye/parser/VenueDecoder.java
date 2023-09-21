package skye.parser;

import skye.data.exception.DukeException;
import skye.data.exception.DukeExceptionType;
import skye.data.venue.Venue;

/**
 * Represents a utility class that decodes a venue from an encoded string
 */
public class VenueDecoder implements Decoder<Venue> {

    /**
     * Decodes the encoded string representation of a venue.
     *
     * @param line The encoded string representing a task in its save format
     * @return Task
     * @throws DukeException When an invalid save format is encountered
     */
    public Venue decode(String line) throws DukeException {
        String[] data = line.split("\\|");
        assert data.length > 0;

        if (data.length != 5) {
            throw new DukeException(DukeExceptionType.INVALID_VENUE_SAVE_FORMAT);
        }

        String name = data[1].trim();
        String address = data[2].trim();
        int capacity = Integer.parseInt(data[3].trim());
        double rent = Double.parseDouble(data[4].trim());

        return new Venue(name, address, capacity, rent);
    }
}

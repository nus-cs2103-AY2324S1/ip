package dukeutilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TimeFormatter class provides methods to parse and format date and time information.
 * It converts date and time strings into LocalDateTime objects and formats them for display.
 */
public class TimeFormatter {
    private String dateTime;
    private LocalDateTime convertedDateTime;

    /**
     * Constructs a TimeFormatter object with the specified date and time string.
     *
     * @param dateTime The date and time string to be formatted.
     */
    public TimeFormatter(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = dateTime;
        this.convertedDateTime = LocalDateTime.parse(dateTime, inputFormatter);
    }

    /**
     * Returns the LocalDateTime representation of the parsed date and time.
     *
     * @return The parsed LocalDateTime object.
     */
    public LocalDateTime getDate() {
        return this.convertedDateTime;
    }

    /**
     * Formats the LocalDateTime object into a formatted date and time string.
     *
     * @return The formatted date and time string.
     */
    public String formatDate() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
        return this.convertedDateTime.format(outputFormatter);
    }


}

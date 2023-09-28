package potato;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The DateTime class represents a date in the Potato application.
 * It provides methods to parse and format dates.
 */
public class DateTime {
    private LocalDate date;
    private String savedDate;

    /**
     * Constructs a DateTime object and initializes it with the provided date string.
     *
     * @param date A string representation of a date in the supported format.
     */
    public DateTime(String date) {
        this.savedDate = date;
        this.date = LocalDate.parse(date);
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

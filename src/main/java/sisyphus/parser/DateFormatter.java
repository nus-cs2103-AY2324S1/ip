package sisyphus.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to format dates.
 */
public class DateFormatter {

    /**
     * Formats localDate into MMM d yyyy format.
     *
     * @param localDate
     * @return formatted date in MMM d yyyy format.
     */
    public static String formatDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

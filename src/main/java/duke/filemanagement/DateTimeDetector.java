package duke.filemanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeDetector {
    // List of formats
    private final DateTimeFormatter t112h = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
    private final DateTimeFormatter t212h = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm");
    private final DateTimeFormatter t124h = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final DateTimeFormatter t224h = DateTimeFormatter.ofPattern("dd-MMM-yyyy HHmm");
    private final DateTimeFormatter t312h = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
    private final DateTimeFormatter t324h = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter d1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter d2 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private final DateTimeFormatter d3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Constructor of DateTimeDetector.
     */
    public DateTimeDetector() {}

    /**
     * Checks if date includes time.
     * @param date Date to be checked.
     * @return Boolean that represents if date includes time.
     */
    boolean includeTime(String date) {
        return date.contains(" ");
    }

    /**
     * Checks if time is in 12h format.
     * @param date Date with time to be checked.
     * @return Boolean that represents if time is in 12h format.
     */
    boolean is12hFormat(String date) {
        return date.contains(":");
    }

    /**
     * Checks if date is split with dash.
     * @param date Date to be checked.
     * @return Boolean that represents if date is split with dash.
     */
    boolean isDateSplitWithDash(String date) {
        return date.contains("-");
    }

    /**
     * Checks if date starts with year.
     * @param date Date to be checked.
     * @return Boolean that represents if date starts with year.
     */
    boolean doesDateStartWithYear(String date) {
        int len = date.split("-")[0].length();
        return len == 4;
    }

    /**
     * Parses a date that includes time.
     * @param date Date to be parsed.
     * @return LocalDate that represents parsed date.
     * @throws DateTimeParseException The date given does not follow any of the formats.
     */
    public LocalDate parseDateWithTime(String date) throws DateTimeParseException {
        if (is12hFormat(date)) {
            if (isDateSplitWithDash(date)) {
                if (doesDateStartWithYear(date)) {
                    // date is yyyy-MM-dd hh:mm
                    return LocalDate.parse(date, t112h);
                } else {
                    // date is dd-MMM-yyyy hh:mm
                    return LocalDate.parse(date, t212h);
                }
            } else {
                // date is dd/MM/yyyy hh:mm
                return LocalDate.parse(date, t312h);
            }
        } else {
            if (isDateSplitWithDash(date)) {
                if (doesDateStartWithYear(date)) {
                    // date is yyyy-MM-dd HHmm
                    return LocalDate.parse(date, t124h);
                } else {
                    // date is dd-MMM-yyyy HHmm
                    return LocalDate.parse(date, t224h);
                }
            } else {
                // date is dd/MM/yyyy HHmm
                return LocalDate.parse(date, t324h);
            }
        }
    }

    /**
     * Parses a date that does not have time.
     * @param date Date to be parsed.
     * @return LocalDate that represents parsed date.
     * @throws DateTimeParseException The date given does not follow any of the formats.
     */
    public LocalDate parseDateOnly(String date) throws DateTimeParseException {
        if (isDateSplitWithDash(date)) {
            if (doesDateStartWithYear(date)) {
                // Case 1: Date is in yyyy-MM-dd
                return LocalDate.parse(date, d1);
            } else {
                // Case 2: Date is dd-MMM-yyyy
                return LocalDate.parse(date, d2);
            }
        } else {
            // Case 3: Date is dd/MM/yyyy
            return LocalDate.parse(date, d3);
        }
    }

    /**
     * Format the input date.
     * @param date Date to be formatted.
     * @return Formatted date.
     */
    public String format(String date) {
        LocalDate d;
        try {
            if (includeTime(date)) {
                d = parseDateWithTime(date);
            } else {
                d = parseDateOnly(date);
            }
            assert d != null : "d be updated with parsed input date and not null";
            return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return date;
        }
    }
}

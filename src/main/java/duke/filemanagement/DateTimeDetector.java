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
    public DateTimeDetector(){}

    /**
     * Format the input date.
     * @param date Date to be formatted.
     * @return Formatted date.
     */
    public String format(String date) {
        LocalDate d;
        if (date.contains(" ")) {
            // date and time given
            try {
                if (date.contains(":")) {
                    // time is 12h format
                    if (date.contains("-")) {
                        int len = date.split("-")[0].length();
                        if (len == 4) {
                            // date is yyyy-MM-dd hh:mm
                            d = LocalDate.parse(date, t112h);
                        } else {
                            // date is dd-MMM-yyyy hh:mm
                            d = LocalDate.parse(date, t212h);
                        }
                    } else {
                        // date is dd/MM/yyyy hh:mm
                        d = LocalDate.parse(date, t312h);
                    }
                } else {
                    // time is 24h format
                    if (date.contains("-")) {
                        int len = date.split("-")[0].length();
                        if (len == 4) {
                            // date is yyyy-MM-dd HHmm
                            d = LocalDate.parse(date, t124h);
                        } else {
                            // date is dd-MMM-yyyy HHmm
                            d = LocalDate.parse(date, t224h);
                        }
                    } else {
                        // date is dd/MM/yyyy HHmm
                        d = LocalDate.parse(date, t324h);
                    }
                }
                return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                return date;
            }

        } else {
            // only date given
            try {
                if (date.contains("-")) {
                    int len = date.split("-")[0].length();
                    if (len == 4) {
                        // Case 1: Date is in yyyy-MM-dd
                        d = LocalDate.parse(date, d1);
                    } else {
                        // Case 2: Date is dd-MMM-yyyy
                        d = LocalDate.parse(date, d2);
                    }
                } else {
                    // Case 3: Date is dd/MM/yyyy
                    d = LocalDate.parse(date, d3);
                }
                return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                return date;
            }
        }
    }
}

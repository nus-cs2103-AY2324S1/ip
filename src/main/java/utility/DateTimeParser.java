package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    public final static String[] DATE_FORMATS = {
            "yyyy-M-d",
            "d-M-yyyy",
            "yyyy/M/d",
            "d/M/yyyy",
    };

    public final static String[] TIME_FORMATS = {
            "HH:mm",
            "HH-mm",
            "HHmm"
    };

    public static LocalDateTime parse(String input) {
        String date = input;
        String time = null;
        String dateFormat = null;

        if (input.split(" ").length == 2) {
            date = input.split(" ")[0];
            time = input.split(" ")[1];
        }

        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
                LocalDate.parse(date, dateFormatter);
                dateFormat = format;
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        if (dateFormat != null) {
            for (String format : TIME_FORMATS) {
                String dateTimeFormat = dateFormat + " " + format;
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
                    return LocalDateTime.parse(input, dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    // do nothing
                }
            }

            if (time == null) {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat)).atStartOfDay();
            }

        }

        return null;
    }

}

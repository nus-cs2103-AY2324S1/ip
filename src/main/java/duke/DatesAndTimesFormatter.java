package duke;

import java.time.format.DateTimeFormatter;

/**
 * Enum class for the different date and time formats.
 */
//@@ author zhanyang01-reused\
//Inspired by the smart usage of enums for date and time formats.
public enum DatesAndTimesFormatter {
    INPUT_FORMAT(DateTimeFormatter.ISO_LOCAL_DATE),
    OUTPUT_FORMAT("MMM dd yyyy"),
    SLASH_FORMAT("dd/MM/yyyy");

    public final DateTimeFormatter formatter;

    DatesAndTimesFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    DatesAndTimesFormatter(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format);
    }
}
//@@ author
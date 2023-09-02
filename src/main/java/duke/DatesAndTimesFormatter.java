package duke;

import java.time.format.DateTimeFormatter;

/**
 * Enum class for the different date and time formats.
 */
//@@ author zhanyang01-reused\
//Inspired by the smart usage of enums for date and time formats.
public enum DatesAndTimesFormatter {
    INPUT_FORMAT("yyyy-MM-dd"),
    OUTPUT_FORMAT("MMM dd yyyy"),
    SLASH_FORMAT("dd/MM/yyyy");

    public final DateTimeFormatter formatter;

    /**
     * Creates a DatesAndTimesFormatter object.
     *
     * @param formatter The DateTimeFormatter to be used.
     */
    DatesAndTimesFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * Creates a DatesAndTimesFormatter object.
     *
     * @param format The format of the DateTimeFormatter to be used.
     */
    DatesAndTimesFormatter(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format);
    }
}
//@@ author
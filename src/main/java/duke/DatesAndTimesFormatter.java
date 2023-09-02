package duke;

import java.time.format.DateTimeFormatter;

public enum DatesAndTimesFormatter {
    INPUT_FORMAT("yyyy-MM-dd"),
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

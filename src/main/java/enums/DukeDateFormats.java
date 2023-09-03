package enums;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public enum DukeDateFormats {
    DATE_ONLY("yyyy-MM-dd");

    private final DateTimeFormatter formatter;

    DukeDateFormats(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public static boolean validate(String string, DukeDateFormats f) {
        try {
            LocalDate.parse(string, f.getFormatter());
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }


}


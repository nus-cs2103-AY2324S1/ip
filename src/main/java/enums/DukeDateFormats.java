package enums;

import java.time.format.DateTimeFormatter;

public enum DukeDateFormats {
    DATE_ONLY("yyyy-MM-dd");

    private final DateTimeFormatter formatter;

    DukeDateFormats(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter getFormatter() {
        return this.formatter;
    }
}


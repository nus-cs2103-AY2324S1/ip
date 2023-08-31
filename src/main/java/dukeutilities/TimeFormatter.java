package dukeutilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeFormatter {
    private String dateTime;
    private LocalDateTime convertedDateTime;

    public TimeFormatter(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        this.dateTime = dateTime;
        this.convertedDateTime = LocalDateTime.parse(dateTime, inputFormatter);
    }

    public LocalDateTime getDate() {
        return this.convertedDateTime;
    }

    public String formatDate() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, h:mm a");
        return this.convertedDateTime.format(outputFormatter);
    }


}

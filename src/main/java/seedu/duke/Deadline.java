package seedu.duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    protected String type = "D";
    protected LocalDateTime dateAndTime;
    public Deadline(String name, LocalDateTime dateAndTime) {
        super(name);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }

    @Override
    public String getTimeInfo() {
        // Format month in words
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH);
        String monthInWords = this.dateAndTime.format(monthFormatter);

        // Format day of the month
        int dayOfMonth = this.dateAndTime.getDayOfMonth();

        // Format year
        int year = this.dateAndTime.getYear();

        // Format the time in AM/PM
        DateTimeFormatter amPmFormatter = DateTimeFormatter.ofPattern("h:mm a");
        LocalTime time = this.dateAndTime.toLocalTime();
        String formattedTime = time.format(amPmFormatter);

        return "(by: " + monthInWords + " " + dayOfMonth + " " + year + ", " + formattedTime + ")";
    }
}

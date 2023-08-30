package seedu.duke;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Encapsulates the Deadline class.
 * Deadlines are tasks with a given deadline.
 */
public class Deadline extends Task {
    protected String type = "D";
    protected LocalDateTime dateAndTime;
    public Deadline(String name, LocalDateTime dateAndTime) {
        super(name);
        this.dateAndTime = dateAndTime;
    }

    /**
     * Obtains the task type in square brackets.
     *
     * @return A string containing the task type in square brackets.
     */
    @Override
    public String getTaskType() {
        return "[" + this.type + "]";
    }

    /**
     * Obtains the deadline in a string format.
     *
     * @return A string containing the deadline information.
     */
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

package Alex;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.Month;
import java.time.LocalDate;

/**
 * A class that can be instantiated to represent the task that has a specific deadline.
 */
public class Deadline extends Task{
    protected String by;

    protected LocalDateTime parsedDateTime;

    public Deadline(String description, String by) throws AlexException, DateTimeParseException {
        super(description);
        this.by = by;
        this.parsedDateTime = LocalDateTime.parse(by, UserInputStorage.TIMEFORMATTER);
    }

    /**
     * A method that is used to get the string representation of the deadline of this task.
     * @return the string representation of the deadline of this task.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * In contrast to getBy method, this method return the deadline of this task as a LocalDate object.
     * @return the deadline of this task as a LocalDate object.
     */
    public LocalDate getDueDate() {
        String dateString = this.by.substring(0, 10);
        LocalDate date = LocalDate.parse(dateString);
        return date;
    }

    @Override
    public String toString() {
        int hour = parsedDateTime.getHour();
        int minute = parsedDateTime.getMinute();
        Month month = parsedDateTime.getMonth();
        int day = parsedDateTime.getDayOfMonth();
        int year = parsedDateTime.getYear();
        return "[D]" + super.toString()
                + " (by: " + hour +":"+ minute + " " + day + " " + month + " " + year + ")";
    }

}

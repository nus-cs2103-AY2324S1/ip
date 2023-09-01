package Jarvis;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that extends the Task class.
 * <p>
 *     The Deadline class is a subclass of the Task class and has 2 additional attributes, by and dateby.
 *     By represents the time to complete the task by.
 *     dateBy is by in a Java LocalDate if the formatting of by is correct.
 * </p>
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate dateBy;

    /**
     * Constructs a new Deadline object.
     *
     * @param description description of the deadline task.
     * @param by when to complete the task by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        stringToDate();
    }

    /**
     * Checks and converts the by string to a valid Java LocalDate if the format is correct
     */
    @Override
    public void stringToDate() {
        Pattern datePattern = Pattern.compile(
                "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                        + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                        + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
        Matcher dateTimeMatcher = datePattern.matcher(this.by);

        if (!dateTimeMatcher.matches()) { // if datetime doesn't match, do nothing
            this.dateBy = null;
        } else {
            Pattern dateTimePattern2 = Pattern.compile(
                    "(\\d{4})-(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])"); // YYYY-MM-DD
            Matcher dateTimePattern2Matcher = dateTimePattern2.matcher(this.by);
            dateTimePattern2Matcher.matches();
            int day = Integer.parseInt(dateTimePattern2Matcher.group(3));
            int month = Integer.parseInt(dateTimePattern2Matcher.group(2));
            int year = Integer.parseInt(dateTimePattern2Matcher.group(1));
            this.dateBy = LocalDate.of(year, month, day);
        }
    }

    /**
     * Converts the task to a user-friendly string representation
     *
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (dateBy == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            String formattedDate = this.dateBy.format(formatter);
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        }
    }
}

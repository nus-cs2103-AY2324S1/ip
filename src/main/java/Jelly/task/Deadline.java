package Jelly.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Corresponds to a task with a deadline(date or date and time).
 */
public class Deadline extends Task {

    protected String by;

    protected LocalDate deadlineDate;

    protected LocalDateTime deadLineDateAndTime;

    /**
     * Constructor for a deadline task.
     *
     * @param description The description of the task.
     * @param by When the task is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.deadlineDate = parseDate(by);
        this.deadLineDateAndTime = parseDateTime(by);
        this.by = by;
    }

    @Override
    public String toString() {
        if (deadlineDate != null) {
            String outputDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return "[D]" + super.toString() + "(by: " + outputDate + ")";
        } else if (deadLineDateAndTime != null) {
            String outputDateTime = deadLineDateAndTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            return "[D]" + super.toString() + "(by: " + outputDateTime + ")";
        } else {
            return "[D]" + super.toString() + "(by: " + by + ")";
        }
    }
    @Override
    public String writeToFile() {
        String printedStuff = "D | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | ";
        return printedStuff + this.by;
    }

    /**
     * Parses the date inputted that corresponds to the formats accepted.
     *
     * @param date The due date of the task.
     * @return The parsed date, or null if the date is in a format that isn't accepted.
     */
    protected LocalDate parseDate(String date) {

        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        formats.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDate.parse(date, formats.get(i));
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Parses the date and time inputted that corresponds to the formats accepted.
     *
     * @param dateTime The due date and time of the task.
     * @return The parsed date and time, or null if incorrect format.
     */
    protected LocalDateTime parseDateTime(String dateTime) {

        List<DateTimeFormatter> formats = new ArrayList<>();
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        formats.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        formats.add(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        for (int i = 0; i < formats.size(); i++) {
            try {
                return LocalDateTime.parse(dateTime, formats.get(i));
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}

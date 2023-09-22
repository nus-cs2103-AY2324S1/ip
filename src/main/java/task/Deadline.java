package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Deadline task class
 */
public class Deadline extends Task {

    protected String deadlineString;
    protected LocalDateTime deadlineDateTime;
    protected boolean isDateTime = false;

    /**
     * Constructor for deadline
     *
     * @param description Task description
     * @param deadlineString String representation of the date of deadline
     */
    public Deadline(String description, String deadlineString) {
        super(description);
        this.deadlineString = deadlineString;
        this.deadlineDateTime = convertDate(deadlineString);
        if (this.deadlineDateTime != null) {
            this.isDateTime = true;
        }
    }

    /**
     * If deadline is a datetime, convert to datetime object
     *
     * @param deadlineString String of the deadline
     */
    public static LocalDateTime convertDate(String deadlineString) {
        List<DateTimeFormatter> formatters = new ArrayList<>();
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MMM/dd HH:mm"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MMM/dd HH:mm:ss"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy/MMM/dd HH:mm:ss.SSS"));
        formatters.add(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));

        deadlineString = deadlineString.trim();
        if (!deadlineString.contains(":")) { // No time
            deadlineString = deadlineString + " 00:00";
        }

        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineString, formatter);
                return deadlineDateTime;
            } catch (DateTimeParseException e) { // task.Deadline is not a DateTime
                // do nothing
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[D]");
        sb.append("[" + getStatusIcon() + "] ");
        sb.append(description);
        sb.append(" (by: " + displayTime() + ")");
        return sb.toString();
    }

    /**
     * Checks if it is a deadline, to differentiate between tasks
     *
     * @return true
     */
    @Override
    public boolean isDeadline() {
        return true;
    }

    /**
     * Returns a boolean of whether the deadline is overdue
     *
     * @return A boolean on whether deadline is overdue or not
     */
    public boolean isOverdue() {
        if (isDateTime) {
            if (deadlineDateTime.isBefore(LocalDateTime.now()) && !this.isDone) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns a boolean of whether the deadline is due by the specified date
     *
     * @param localDateTime Date and Time of the deadline
     * @return boolean
     */
    public boolean isDueBy(LocalDateTime localDateTime) {
        if (isDateTime) {
            try {
                if (deadlineDateTime.isBefore(localDateTime) && !this.isDone) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Display a Date Time object in a nicer string format
     *
     * @return String
     */
    public String displayTime() {
        if (isDateTime) {
            return deadlineDateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")).replaceAll("[T\\-/]", " ");
        } else {
            return deadlineString;
        }
    }

    @Override
    public boolean isDuplicate(Task task) {
        if (!(task instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) task;
        if (deadline.description.equals(this.description) && deadline.deadlineDateTime.equals(this.deadlineDateTime)) {
            return true;
        } else {
            return false;
        }
    }
}

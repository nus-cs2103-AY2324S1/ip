package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 *
 * @author Qin Yan Er
 */
public class Deadline extends Task {
    protected String dueDate;
    protected String[] parts;
    protected String date;
    protected String time;

    /**
     * Creates a new Deadline instance.
     *
     * @param description The description of the task.
     * @param dueDate The deadline of the task.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Extracts and processes the date and time from the 'by' string.
     */
    public void dateTime() {
        parts = this.dueDate.split("/");
        String day;
        String month;
        String year;

        if (parts.length != 1) {

            if (parts[0].length() == 1) {
                day = "0" + parts[0];
            } else {
                day = parts[0];
            }

            if (parts[1].length() == 1) {
                month = "0" + parts[1];
            } else {
                month = parts[1];
            }

            String[] yearTime = parts[2].split("\\s+");

            if (yearTime.length == 1) {
                year = parts[2];
            } else {
                year = yearTime[0];
                time = yearTime[1].substring(0, 2) + ":" + yearTime[1].substring(2);
            }

            date = year + "-" + month + "-" + day;
        }
    }

    /**
     * Returns a formatted string to save the task to a file.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String saveTask() {
        return "D" + " | " + super.saveTask() + " | " + this.dueDate;
    }

    /**
     * Returns a formatted string for display.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        dateTime();

        if (parts.length == 1) {
            return "[D]" + super.toString() + " (by: " + dueDate + ")";
        } else {
            LocalDate deadline = LocalDate.parse(date);
            if (time == null) {
                return "[D]" + super.toString() + " (by: " +
                        deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
            } else {
                LocalTime deadlineTime = LocalTime.parse(time);
                return "[D]" + super.toString() + " (by: " +
                        deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", " +
                        deadlineTime.format(DateTimeFormatter.ofPattern("hh:mma")) + ")";
            }
        }
    }
}

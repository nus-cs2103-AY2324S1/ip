package taskmate.tools.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a child class of the Task class that represents a 'Event' type task specified by the user.
 */
public class Event extends Task {

    LocalDate startDatetime;
    LocalDate endDatetime;

    /**
     * Event constructor that allows the developer to specify the name of the task, a date that represents
     * the date that this task starts, and a date that represents the date that this task ends. These dates must
     * be String instances in the form "YYYY-mm-dd" to be parsed to `LocalDate` instances.
     *
     * @param name the name of the event task.
     * @param startDatetime the date that the event task starts. It has to be of the form "YYYY-mm-dd".
     * @param endDatetime the date that the event task ends. It has to be of the form "YYYY-mm-dd".
     */
    public Event(String name, String startDatetime, String endDatetime) {
        super(name);
        this.startDatetime = LocalDate.parse(startDatetime);
        this.endDatetime = LocalDate.parse(endDatetime);
    }

    /**
     * Event constructor that allows the developer to specify the name of the task, a date that represents
     * the date that this task starts, and a date that represents the date that this task ends. These dates must be
     * `LocalDate` instances.
     *
     * @param name the name of the event task.
     * @param startDatetime the date that the event task starts.
     * @param endDatetime the date that the event task ends.
     */
    public Event(String name, LocalDate startDatetime, LocalDate endDatetime) {
        super(name);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    /**
     * Event constructor that allows the developer to specify the name of the task, a date that represents
     * the date that this task starts, a date that represents the date that this task ends, and a boolean that
     * represents if the task has been completed. These dates must be String instances in the form "YYYY-mm-dd" to be
     * parsed to `LocalDate` instances.
     *
     * @param name the name of the event task.
     * @param startDatetime the date that the event task starts. It has to be of the form "YYYY-mm-dd".
     * @param endDatetime the date that the event task ends. It has to be of the form "YYYY-mm-dd".
     * @param isDone a boolean variable that represents if the task has been completed.
     */
    public Event(String name, String startDatetime, String endDatetime, boolean isDone) {
        super(name, isDone);
        this.startDatetime = LocalDate.parse(startDatetime);
        this.endDatetime = LocalDate.parse(endDatetime);
    }

    /**
     * Event constructor that allows the developer to specify the name of the task, a date that represents
     * the date that this task starts, a date that represents the date that this task ends, and a boolean that
     * represents if the task has been completed. These dates must be `LocalDate` instances.
     *
     * @param name the name of the event task.
     * @param startDatetime the date that the event task starts.
     * @param endDatetime the date that the event task ends.
     * @param isDone a boolean variable that represents if the task has been completed.
     */
    Event(String name, LocalDate startDatetime, LocalDate endDatetime, boolean isDone) {
        super(name, isDone);
        this.startDatetime = startDatetime;
        this.endDatetime = endDatetime;
    }

    @Override
    String getTaskType() {
        return "Event";
    }

    String getStartDatetimeFormatted() {
        return this.startDatetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    String getEndDatetimeFormatted() {
        return this.endDatetime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    String getStartDatetime() {
        return this.startDatetime.toString();
    }

    String getEndDatetime() {
        return this.endDatetime.toString();
    }

    @Override
    public String toString() {
        return "[E][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name + " (from: " + this.getStartDatetimeFormatted() + " to: " + this.getEndDatetimeFormatted() + ")";
    }

    public String formatTaskForSaving() {
        return "[E][" + (this.getIsDone() ? 'X' : ' ') + "] " + this.name + " (from: " + this.getStartDatetime() + " to: " + this.getEndDatetime() + ")";
    }
}

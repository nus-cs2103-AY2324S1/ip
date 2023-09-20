package duke.tasks;

import java.time.LocalDate;

import duke.utils.DukeDateFormat;

/** Encapsulates a task with a from and to date. */
public class Event extends Task {

    /** Date where the event start. */
    private LocalDate from;
    /** Date where the event ends. */
    private LocalDate to;

    /**
     * Constructor for Event.
     * @param taskName Name of event.
     * @param from Date where event starts.
     * @param to Date where event ends.
     */
    public Event(String taskName, int isDone, LocalDate from, LocalDate to) {
        super(taskName, isDone);
        this.from = from;
        this.to = to;
    }


    /**
     * Returns duration of the event.
     * @return Duration of event.
     */
    private String getDuration() {
        return DukeDateFormat.dateToString(from) + " - " + DukeDateFormat.dateToString(to);
    }

    /**
     * Returns the string representation of the status of the event.
     * @return Status of the event.
     */
    @Override
    public String getTask() {
        return "Event ->" + super.getTask() + " (" + getDuration() + ")";
    }

    /**
     * Returns the string representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String fromDate = from.toString();
        String toDate = to.toString();
        return super.toString().replace("/TASK", "event ") + " /from " + fromDate + " /to " + toDate;
    }
}

package ratspeak.task;

import ratspeak.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event.
 * It has a start date and end date.
 */
public class Event extends Task {
    private final LocalDate deadlineDate;
    private final LocalDate startDate;


    private static String[] parseEvent(String task) throws DukeException {
        // index 0: task name, index 1: task time range
        String [] taskSplit = task.split("/from", 2);
        String taskName = taskSplit[0].trim();

        if (taskName.isEmpty()) {
            throw new DukeException("Please enter task name");
        }

        if (taskSplit.length != 2) {
            throw new DukeException("Please enter valid Event (make sure to start /from)");
        }

        if (taskSplit[1].trim().isEmpty()) {
            throw new DukeException("Please enter valid Event (make sure to enter valid format)");
        }

        String taskTimeRange = taskSplit[1];
        String [] parseTimeRange = taskTimeRange.split("/to", 2);

        if (parseTimeRange.length != 2) {
            throw new DukeException("Please enter valid Event (make sure to have /to to clarify the end date)");
        }
        if (parseTimeRange[1].trim().isEmpty()) {
            throw new DukeException("Please enter valid end date");
        }

        return new String[] {taskName, parseTimeRange[0].trim(), parseTimeRange[1].trim()};
    }

    /**
     * Initialize Event object that models an event
     * @param task the deadline task name with a "/from" and "/to" separating the description and the start and end date
     * @throws DukeException If the description, start date or end date is empty or there is no "/from" or "/to"
     * the datetime format is wrong (not YYYY-MM-DD)
     */
    public Event(String task) throws DukeException {
        super(parseEvent(task)[0]);
        try {
            this.deadlineDate = LocalDate.parse(parseEvent(task)[2]);
            this.startDate = LocalDate.parse(parseEvent(task)[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong date time format: Use YYYY-MM-DD\n");
        }

    }

    private Event(String task, boolean isDone, LocalDate deadlineDate, LocalDate startDate) {
        super(task, isDone);
        this.deadlineDate = deadlineDate;
        this.startDate = startDate;
    }

    /**
     * Returns new Event object that is marked
     * @return Event object that is marked
     */
    @Override
    public Event done() {
        return new Event(super.getTask(), true, this.deadlineDate, this.startDate);
    }

    /**
     * Returns new Event object that is unmarked
     * @return Event object that is unmarked
     */
    @Override
    public Event undone() {
        return new Event(super.getTask(), false, this.deadlineDate, this.startDate);
    }

    /**
     * Returns format of string to be stored in hard disk
     * @return string
     */
    @Override
    public String storageText() {
        String start = startDate.toString();
        String end = deadlineDate.toString();
        return "[E]" + super.toString() + " /from " + start + " /to " + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}

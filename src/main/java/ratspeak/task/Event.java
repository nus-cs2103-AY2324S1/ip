package ratspeak.task;

import ratspeak.exception.DukeException;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * represents an event.
 * It has a start date and end date.
 */
public class Event extends Task {
    private final LocalDate deadlineDate;
    private final LocalDate startDate;


    private static String parseTaskName(String task) throws DukeException {

        String [] taskSplit = task.split("/from", 2);
        String taskName = taskSplit[0].trim();

        if (taskName.isEmpty()) {
            throw new DukeException("Please enter task name\n");
        }

        return taskName;
    }

    private static LocalDate parseStartDate(String task) throws DukeException {
        String [] taskSplit = task.split("/from", 2);
        if (taskSplit.length != 2) {
            throw new DukeException("Please enter valid Event (make sure to start /from)\n");
        }
        String taskTimeRange = taskSplit[1].trim();
        if (taskTimeRange.isEmpty()) {
            throw new DukeException("Please enter valid Event (make sure to enter valid format)\n");
        }
        String [] parseTimeRange = taskTimeRange.split("/to", 2);
        try {
            return LocalDate.parse(parseTimeRange[0].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong date time format for start date: Use YYYY-MM-DD\n");
        }
    }


    private static LocalDate parseEndDate(String task) throws DukeException {
        parseTaskName(task);
        parseStartDate(task);
        String [] taskSplit = task.split("/from", 2);
        String taskTimeRange = taskSplit[1].trim();
        String [] parseTimeRange = taskTimeRange.split("/to", 2);
        try {
            return LocalDate.parse(parseTimeRange[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong date time format for end date: Use YYYY-MM-DD\n");
        }
    }

    /**
     * initialize Event object that models an event
     * @param task the deadline task name with a "/from" and "/to" separating the description and the start and end date
     * @throws DukeException If the description, start date or end date is empty or there is no "/from" or "/to"
     * the datetime format is wrong (not YYYY-MM-DD)
     */
    public Event(String task) throws DukeException {
        super(parseTaskName(task), parseEndDate(task));
        this.deadlineDate = parseEndDate(task);
        this.startDate = parseStartDate(task);

    }

    private Event(String task, boolean isDone, LocalDate deadlineDate, LocalDate startDate) {
        super(task, deadlineDate, isDone);
        this.deadlineDate = deadlineDate;
        this.startDate = startDate;
    }


    /**
     * returns new Event object that is marked
     * @return Event object that is marked
     */
    @Override
    public Event done() {
        return new Event(super.getTask(), true, this.deadlineDate, this.startDate);
    }

    /**
     * returns new Event object that is unmarked
     * @return Event object that is unmarked
     */
    @Override
    public Event undone() {
        return new Event(super.getTask(), false, this.deadlineDate, this.startDate);
    }

    /**
     * returns format of string to be stored in hard disk
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

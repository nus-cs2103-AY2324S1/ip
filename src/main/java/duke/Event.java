package duke;

import duke.DukeException;
import duke.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        String taskTimeRange = taskSplit[1];
        String [] parseTimeRange = taskTimeRange.split("/to", 2);

        if (parseTimeRange.length != 2) {
            throw new DukeException("Please enter valid Event (make sure to have /by to clarify the end date)");
        }
        if (parseTimeRange[1].trim().isEmpty()) {
            throw new DukeException("Please enter valid end date");
        }

        String eventInfoString = taskSplit[0];

        return new String[] {eventInfoString, parseTimeRange[0].trim(), parseTimeRange[1].trim()};
    }
    public Event(String task) throws DukeException {
        super(parseEvent(task)[0]);
        try {
            this.deadlineDate = LocalDate.parse(parseEvent(task)[2]);
            this.startDate = LocalDate.parse(parseEvent(task)[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong date time format: Use YYYY-MM-DD\n");
        }

    }

    public Event(String task, boolean isDone, LocalDate deadlineDate, LocalDate startDate) {
        super(task, isDone);
        this.deadlineDate = deadlineDate;
        this.startDate = startDate;
    }
    @Override
    public Event done() {
        return new Event(super.getTask(), true, this.deadlineDate, this.startDate);
    }
    @Override
    public Event undone() {
        return new Event(super.getTask(), false, this.deadlineDate, this.startDate);
    }

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

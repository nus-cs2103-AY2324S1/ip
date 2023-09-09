package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Task which contains a date to start and date to finish. */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Class constructor specifying the description of the event task.
     * @param description the string description of the event.
     * @param from the string description of the date to begin the event.
     * @param to the string description of the date to end the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns a string description which contains the starting and ending time of the event.
     */
    @Override
    public String toString() {
        assert this.from != null;
        assert this.to != null;
        String startingDate = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endingDate = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startingDate, endingDate);
    }

    /**
     * Returns the information associated with the event to be stored on local hard disk.
     * @return the string representation of the event saved onto the local hard disk.
     */
    public String saveTask() {
        String isDoneMarker = this.isDone() ? "X" : " ";
        return String.format("E|%s|%s|%s|%s", isDoneMarker, this.getDescription(), this.from, this.to);
    }

}

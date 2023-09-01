package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /*
        The start date and time of the period of the event.
     */
    private LocalDateTime start;
    /*
        The end date and time of the period of the event.
     */
    private LocalDateTime end;

    /**
     * Constructs an Event object with the provided description, status, and start and end date and time for the period.
     *
     * @param description The description of the task.
     * @param isDone True if the task is done else otherwise.
     * @param start The start date and time.
     * @param end The end date and time.
     */
    public Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {

        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Generates the format of the task to be written into the text file.
     *
     * @return The format of the task in the text file.
     */
    @Override
    public String contentLine() {
        return "E" + super.contentLine() + "/" + this.start.toString() + "/" + this.end.toString();
    }

    /**
     * Overrides the 'toString' method of the parent class with to display the task in different format.
     *
     * @return The appearance of the task in the application.
     */
    @Override
    public String toString() {

        String result = "[E]" + super.toString() + " (from: " + formatDate(this.start) + " to: " +
                formatDate(this.end) + ")";
        return result;
    }

    /**
     * Convert the date and time provided into another format.
     *
     * @param l The date and time to be changed.
     * @return The date and time in "dd/MMM/yyyy HH:mm" format.
     */
    public String formatDate(LocalDateTime l) {
        return l.format(DateTimeFormatter.ofPattern("dd/MMM/yyyy HH:mm"));
    }
}

package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A subclass of Task. Contains a description, isDone, from and to.
 */
public class Event extends Task {
    /**
     * from contains the dateTime at which the Task starts.
     * to contains the dateTime at which the Task ends.
     */
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event class.
     *
     * @param description Name of the Task.
     * @param from Start of the Task.
     * @param to End of the Task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for the Event class.
     *
     * @param description Name of the Task.
     * @param from Start of the Task.
     * @param to End of the Task.
     * @param isDone If task is completed.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the String representation of the Event, along with the
     * indication of whether it isDone and the start and end of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String outputFrom = from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        String outputTo = to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));

        return "[E]" + super.toString() + " (from: " + outputFrom + " to: " + outputTo + ")";
    }

    /**
     * Returns the String to be written into the .txt file for saving
     * of the file.
     *
     * @return String to be written into the save file.
     */
    @Override
    public String write() {
        String outputFrom = from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String outputTo = to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        return "E | " + super.write() + " | " + outputFrom + " | " + outputTo;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }
}
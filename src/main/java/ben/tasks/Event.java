package ben.tasks;

import ben.tasks.Task;

import java.time.LocalDateTime;

/**
 * Represents an events task.
 */
public class Event extends Task {
    /**
     * The date of when the event starts.
     */
    private LocalDateTime from;

    /**
     * The date of when the event ends.
     */
    private LocalDateTime to;

    /**
     * Takes in description of the event, whether it is completed, the from and to date of the event.
     *
     * @param description The description of the event.
     * @param isCompleted Whether the event is completed.
     * @param from The date of when the event starts.
     * @param to The date of when the event ends.
     */
    public Event(String description, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    /**
     * Represents the event.
     *
     * @return String representation of events.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateFormat(from) + "HRS to: " + dateFormat(to) + "HRS)";
    }

    /**
     * Represents events when it is saved to the file.
     *
     * @return String representation of events.
     */
    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + saveDateFormat(from) + "|" + saveDateFormat(to);
    }
}

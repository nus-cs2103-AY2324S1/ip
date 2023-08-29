package duke.tasks;

import duke.exceptions.DukeInvalidArgumentException;

/**
 * A task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {

    // The start time of the event.
    private String startTime;

    // The end time of the event.
    private String endTime;

    /**
     * Creates a new Event object.
     *
     * @param description The description of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, String startTime, String endTime) throws DukeInvalidArgumentException {
        super(description);
        this.startTime = Task.parseDateInput(startTime);
        this.endTime = Task.parseDateInput(endTime);
    }

    @Override
    public String getSaveFormat() {
        return String.format("EVENT||%d||%s /from %s /to %s\n", this.getIsDone() ? 1 : 0, this.getDescription(),
                this.startTime, this.endTime);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), Task.getDateOutputString(startTime),
                Task.getDateOutputString(endTime));
    }
}

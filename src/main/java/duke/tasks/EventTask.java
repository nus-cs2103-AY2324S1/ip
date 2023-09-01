package duke.tasks;

import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents an "Event" task in the Duke application, which includes a specific start date and time and end date and
 * time.
 */
public class EventTask extends Task {

    /** The start date and time for the task. */
    private LocalDateTime from;

    /** The end date and time for the task. */
    private LocalDateTime to;

    /**
     * Constructs an EventTask with the specified name, start date and time, and end date and time.
     *
     * @param name The name or description of the EventTask.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public EventTask(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted description of the EventTask, including the task type identifier "[E]", start, and end times.
     *
     * @return The formatted description of the EventTask.
     */
    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + from.format(Ui.DATE_FORMAT_OUTPUT) + " to: " + to.format(Ui.DATE_FORMAT_OUTPUT) + ")";
    }

    /**
     * Converts the EventTask to a string representation for storage in a file.
     *
     * @return The string representation for file storage.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(Ui.DATE_FORMAT_OUTPUT) + " | " + to.format(Ui.DATE_FORMAT_OUTPUT);
    }

    /**
     * Returns a string representation of the EventTask, including the start and end times.
     *
     * @return The string representation of the EventTask.
     */
    @Override
    public String toString() {
        return super.toString() + " | " + from.format(Ui.DATE_FORMAT_OUTPUT) + " | " + to.format(Ui.DATE_FORMAT_OUTPUT);
    }
}

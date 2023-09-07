package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task with an event type in the Duke application.
 * It extends the base Task class and includes an additional field for the event date.
 */
public class EventTask extends Task {
    private LocalDate atDate;

    /**
     * Constructs an EventTask with the specified description, event date, and completion status.
     *
     * @param description The description of the event task.
     * @param atDate      The date of the event.
     * @param isDone      The completion status of the task (true if done, false otherwise).
     */
    public EventTask(String description, LocalDate atDate, boolean isDone) {
        super(description, isDone);
        this.atDate = atDate;
    }

    /**
     * Returns a string representation of the EventTask, including task type, completion status, and description details.
     *
     * @return A string representation of the EventTask.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + getDescriptionDetails();
    }

    /**
     * Converts the EventTask to a string format suitable for saving to a file, including task type, completion status,
     * description, and event date.
     *
     * @return A string formatted for file storage.
     */
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s (at: %s)", getTaskType(), isDone ? 1 : 0, description,
                atDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Returns the task type identifier for an event task (i.e., "E").
     *
     * @return The task type identifier.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the description details of the event task.
     *
     * @return The event task name together with the at date.
     */
    private String getDescriptionDetails() {
        return description + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
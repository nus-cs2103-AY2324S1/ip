package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Event` class represents a task with a description and a from and to date and time.
 * It is a subclass of the `Task` class.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new `Event` task with the given description, start date and time, and end date and time.
     * @param description The description of the event task.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task for display to the user.
     * @return A formatted string containing the task type, status, description, start date, and end date.
     */
    @Override
    public String toString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[E]" + super.toString() + " (from: " + from.format(formatter) + " to: " + to.format(formatter) + ")";
    }

    /**
     * Converts the event task to a string representation for saving to a file.
     * @return A formatted string representing the task type, status, description, start date, and end date.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E" + " | " + super.toFileString() + " | " + from.format(formatter) + " | " + to.format(formatter);
    }

    /**
     * Creates an `Event` task object from a data string. Used for deserialization.
     * @param taskData The data string containing event task information.
     * @return An `Event` task object created from the data string, or `null` if the data is incomplete or invalid.
     */
    public static Event createEventFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 4 && taskParts[0].trim().equals("E")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime from = LocalDateTime.parse(taskParts[3].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(taskParts[4].trim(), formatter);

            Event event = new Event(description, from, to);
            if (doneStatus.equals("1")) {
                event.markDone();
            }
            return event;
        }
        return null; // incomplete data.txt
    }
}

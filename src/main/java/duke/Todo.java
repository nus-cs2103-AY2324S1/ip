package duke;

import java.time.LocalTime;

/**
 * Represents a todo task without any specific deadline or timeframe.
 * Inherits from the Task class.
 */
public class Todo extends Task {
    private LocalTime duration;

    /**
     * Constructs a Todo object with the provided description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object with the provided description and duration.
     *
     * @param description The description of the todo task.
     * @param duration The duration of the todo task.
     */
    public Todo(String description, LocalTime duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Parses the duration of the todo task.
     *
     * @param duration The duration of the todo task.
     * @return The duration of the todo task in LocalTime format.
     */
    public static LocalTime parseDuration(String duration) {
        if (duration.contains("hour") || duration.contains("hr")) {
            String[] durationArr = duration.split(" ");
            int hours = Integer.parseInt(durationArr[0]);
            return LocalTime.of(hours, 0);
        } else if (duration.contains("minute") || duration.contains("min")) {
            String[] durationArr = duration.split(" ");
            int minutes = Integer.parseInt(durationArr[0]);
            return LocalTime.of(0, minutes);
        } else {
            return LocalTime.of(0, 0);
        }
    }

    /**
     * Converts the Todo object to a string representation.
     *
     * @return The formatted string representation of the Todo object.
     */
    @Override
    public String toString() {
        if (this.duration != null) {
            return "[T]" + "[" + this.getStatusIcon() + "] " + super.toString() + " (takes: " + this.duration + ")";
        } else {
            return "[T]" + "[" + this.getStatusIcon() + "] " + super.toString();
        }
    }
}

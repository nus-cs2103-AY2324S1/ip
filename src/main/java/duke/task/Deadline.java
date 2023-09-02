package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `Deadline` class represents a task with a description and a deadline date and time.
 * It is a subclass of the `Task` class.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a new `Deadline` task with the given description and deadline date and time.
     * @param description The description of the deadline task.
     * @param by The deadline date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task for display to the user.
     * @return A formatted string containing the task type, status, description, and deadline.
     */
    @Override
    public String toString () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Converts the deadline task to a string representation for saving to a file.
     * @return A formatted string representing the task type, status, description, and deadline.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D"+ " | " + super.toFileString() + " | " + by.format(formatter);
    }

    /**
     * Creates a `Deadline` task object from a data string. Used for deserialization.
     * @param taskData The data string containing deadline task information.
     * @return A `Deadline` task object created from the data string, or `null` if the data is incomplete or invalid.
     */
    public static Deadline createDeadlineFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3 && taskParts[0].trim().equals("D")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime by = LocalDateTime.parse(taskParts[3].trim(), formatter);

            Deadline deadline = new Deadline(description, by);
            if (doneStatus.equals("1")) {
                deadline.markDone();
            }
            return deadline;
        }
        return null; // incomplete data.txt
    }
}

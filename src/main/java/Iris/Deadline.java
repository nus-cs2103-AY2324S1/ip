/**
 * Represents a task with a deadline.
 */
package iris;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDate deadlineDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param name          The name of the deadline task.
     * @param deadlineString The string representation of the deadline date.
     */
    public Deadline(String name, String deadlineString) {
        super(name);
        this.deadlineString = deadlineString;
        this.deadlineDate = parseDateTime(deadlineString);
    }

    /**
     * Get the deadline string of the task.
     *
     * @return The deadline string.
     */
    public String getDeadlineString() {
        return this.deadlineString;
    }

    /**
     * Generate a string representation of the task for writing to a file.
     *
     * @return The formatted string for file output.
     */
    public String writeTaskToFile() {
        return String.format("%s | %s | %s | %s", "D",
                this.isDone() ? 1 : 0,
                this.getName(),
                this.getDeadlineString());
    }

    /**
     * Create a Deadline task object from an array of strings read from a file.
     *
     * @param args The array of strings containing task information.
     * @return The Deadline task object.
     */
    public static Deadline readTaskFromFile(String[] args) {
        Deadline newDeadlineTask = new Deadline(args[2], args[3]);
        if (args[1].equals("1")) {
            newDeadlineTask.markDone();
        }
        return newDeadlineTask;
    }

    /**
     * Parse the deadline string and convert it to a LocalDate object.
     *
     * @param deadlineString The string representation of the deadline date.
     * @return The LocalDate object representing the deadline date.
     */
    private LocalDate parseDateTime(String deadlineString) {
        try {
            return LocalDate.parse(deadlineString);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Override the toString method to provide a custom string representation of the Deadline task.
     *
     * @return The formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        String deadline;
        if (deadlineDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            deadline = deadlineDate.format(formatter);
        } else {
            deadline = deadlineString;
        }
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}

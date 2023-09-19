package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task in the chatbot application.
 */
public class Deadline extends Task {
    private final LocalDateTime time;

    /**
     * Constructs a Deadline task with the given name and time.
     *
     * @param name The name of the deadline task.
     * @param time The deadline date and time as a string.
     */
    public Deadline(String name, String time) {
        super(name);
        this.time = timeConverter(time);
    }

    /**
     * Converts a string time representation to a LocalDateTime object.
     *
     * @param time The time as a string.
     * @return The LocalDateTime object representing the time.
     * @throws DateTimeException If there's an issue parsing the time string.
     */
    public LocalDateTime timeConverter(String time) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        if (!time.contains(" ")) {
            time += " 2359";
        }
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Converts the deadline task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    @Override
    public String toSave() {
        String timeToSave = time.toString().replace("T", " ").replace(":", "");
        return (super.isComplete ? "1 " : "0 ") + "deadline " + super.name + "/by " + timeToSave;
    }

    /**
     * Converts the deadline task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
}

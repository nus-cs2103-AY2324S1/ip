package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * The Deadline class represents a task with a specific deadline date and time.
 * It is a subclass of the Task class and provides methods to work with deadline tasks.
 */
public class Deadline extends Task {
    LocalDateTime dateTime;

    /**
     * Constructs a Deadline task with the given task name and deadline time.
     *
     * @param taskName The name of the deadline task.
     * @param time The deadline date and time for the task.
     */
    public Deadline(String taskName, LocalDateTime time) {
        super(taskName);
        this.dateTime = time;
    }

    /**
     * Constructs a Deadline task with the given task name, completion status, and deadline time.
     *
     * @param taskName The name of the deadline task.
     * @param done The completion status of the task.
     * @param time The deadline date and time for the task.
     */
    public Deadline(String taskName, boolean done, LocalDateTime time) {
        super(taskName, done);
        this.dateTime = time;
    }

    /**
     * Overrides the toString() method to provide a custom string representation of the task.
     *
     * @return A formatted string containing task details and deadline information.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US);
        String formattedDate = dateTime.format(formatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}


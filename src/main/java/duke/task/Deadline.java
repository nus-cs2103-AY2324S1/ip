package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific due date.
 */
public class Deadline extends Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LocalDateTime duedate;

    /**
     * Constructs a Deadline task with a description, due date and completion status.
     *
     * @param task The description of the task.
     * @param duedate The due date and time of the task.
     * @param done The completion status of the task.
     */
    public Deadline(String task, String duedate, boolean done) {
        super(task, done);
        this.duedate = stringToDateObj(duedate);
    }

    private LocalDateTime stringToDateObj(String dateString) {
        return LocalDateTime.parse(dateString, formatter);
    }

    /**
     * Returns a string representation of the Deadline task for Ui display..
     *
     * @return A formatted string including the task type, description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.duedate.format(formatter));
    }

    /**
     * Returns a string representation of Deadline task for saving to a file.
     *
     * @return A formatted string suitable for saving to a file.
     */
    @Override
    public String getTaskFileString() {
        return "D" + " , " + super.getTaskFileString() + " , " + this.duedate.format(formatter);
    }
}

package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime date;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     *
     * @param status 0 for uncompleted, 1 or other numbers for completed
     * @param task task description
     * @param date deadline of the task
     */
    public Deadline(int status, String task, LocalDateTime date) {
        super(status, task);
        this.date = date;
    }

    /**
     * Converts Deadline to the correct string format to write to
     * data file.
     *
     * @return string to write to data file
     */
    @Override
    public String convertTask() {
        return "D | " +  super.getStatus() + " | " + super.getTask() +
                " | " + this.date.format(formatter);
    }

    /**
     * Returns string representation of a Deadline object.
     *
     * @return string Deadline
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.date.format(formatter) + ")";
    }
}

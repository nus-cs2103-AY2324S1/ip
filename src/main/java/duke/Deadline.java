package duke;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructs a Deadline object.
     * 
     * @param taskName
     * @param deadline Deadline in the format yyyy-mm-dd.
     */
    public Deadline(String taskName, String deadline) {
        super(taskName);
        // assume deadline is of the format 2019-12-01
        this.deadline = LocalDate.parse(deadline);
    }

    private String dateToString() {
        // desired format is MMM dd yyyy
        // convert deadline to MMM dd yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return this.deadline.format(formatter);
    }

    private String parseDate() {
        // convert deadline back to yyyy-mm-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.deadline.format(formatter);
    }

    /**
     * Returns a string representation of the Deadline object.
     * 
     * @return String representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateToString() + ")";
    }

    /**
     * Returns a string representation of the Deadline object to be saved in the
     * file.
     * 
     * @return String representation of the Deadline object to be saved in the file.
     */
    @Override
    public String saveString() {
        return "D | " + (this.getIsDone() ? "1" : "0") + " | " + this.getTaskName() + " | " + parseDate();
    }
}

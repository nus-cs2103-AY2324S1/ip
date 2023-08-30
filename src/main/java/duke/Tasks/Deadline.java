package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Duke application.
 * This class extends the base class Task and includes the due date of the deadline.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;
    private String printDateString;
    private String saveDateString;

    /**
     * Constructs a new Deadline object with a title and due date.
     *
     * @param title    The title of the deadline task.
     * @param dueDate  The due date of the deadline.
     */
    public Deadline(String title, LocalDateTime dueDate, boolean isMarked) {
        super(title, isMarked);
        this.dueDate = dueDate;
        this.printDateString = this.dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.saveDateString = this.dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Generates a string representation of the Deadline object.
     *
     * @return A formatted string indicating the status, title, and due date of the deadline.
     */
    @Override
    public String toString() {
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[D]" + mark + title + " (by: " + this.printDateString + ")";
    }

    @Override
    public String toSave() {
        String res = "D";
        res += (isMarked ? "| 1" : "| 0");
        res += "| " + title;
        res += "| " + saveDateString;
        return res;
    }

    public LocalDateTime getDueDate() {
        return this.dueDate;
    }
}


package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Class representing a task which has a deadline */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Class constructor specifying the description of the deadline task.
     * @param description the string description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string description which contains the deadline of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the information associated with the deadline to be stored on local hard disk.
     * @return the string representation of the deadline saved onto the local hard disk.
     */
    public String saveTask() {
        return "D|" + (this.isDone() ? "X|" : " |") + this.getDescription() + "|" + this.by;
    }
}


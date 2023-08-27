package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Class constructor specifying the description of the deadline task.
     * @param description the string description of the task
     * @param by the deadline of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string description which contains the deadline of the task
     * @return a string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String saveTask() {
        return "D|" + (this.isDone() ? "X|" : " |") + this.getDescription() + "|" + this.by;
    }
}


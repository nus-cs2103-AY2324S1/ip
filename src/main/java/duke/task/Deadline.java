package duke.task;

import duke.InvalidTaskCreationException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public static Deadline DeadlineCon(String description, String by) throws InvalidTaskCreationException, DateTimeParseException {
        if (description.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The description of a duke.task.Deadline duke.task.Task cannot be empty.");
        } else if (by.equalsIgnoreCase("")) {
            throw new InvalidTaskCreationException("OOPS!!! The deadline time of a duke.task.Deadline duke.task.Task cannot be empty.");
        } else {
            LocalDateTime deadlineDate = LocalDateTime.parse(by, Task.DTformatter);
            return new Deadline(description, deadlineDate);
        }
    }

    public LocalDateTime getUrgencyDate() {
        return this.by;
    }

    @Override
    public String toString() {

        // Format the LocalDateTime object to a string
        String formattedDate = this.by.format(Task.outputFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }
}

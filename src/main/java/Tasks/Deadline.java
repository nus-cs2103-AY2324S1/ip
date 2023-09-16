package Tasks;
import Exceptions.DukeException;
import Exceptions.InvalidTaskFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline. A <code>Deadline</code> object
 * has a <code>LocalDate</code> deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    public Deadline(String description) throws DukeException {
        super(description);
        initializeDeadline();
    }

    private void initializeDeadline() throws DukeException{
        String[] splitString = description.split("/by", 2);
        if (splitString.length != 2) throw new InvalidTaskFormatException();

        assert !splitString[0].isBlank() : "description should not be empty";
        this.description = splitString[0].trim();
        assert !splitString[1].isBlank() : "deadline should not be empty";
        String deadlineString = splitString[1].trim();
        this.deadline = super.parseDate(deadlineString);
    }

    public Deadline(String description, String deadlineString) {
        super(description);
        // deadline will be in default yyyy-MM-dd format which can be parsed by LocalDate
        this.deadline = LocalDate.parse(deadlineString);
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        if (deadline == null) return super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return super.toString() + String.format(" (by: %s)",deadline.format(formatter));
    }

    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + (deadline == null ? "" : " | " + deadline.toString());
    }
}

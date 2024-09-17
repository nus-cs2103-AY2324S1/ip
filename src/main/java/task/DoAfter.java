package task;

import exception.DukeException;
import exception.InvalidTaskFormatException;
import exception.TaskSpecificException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a DoAfter task. A <code>DoAfter</code> object
 * has a <code>LocalDate</code> doAfter date that a task can be done after.
 */
public class DoAfter extends Task{

    private LocalDate doAfter;

    /**
     * Creates a new DoAfter task.
     * @param description The description of the task.
     * @throws DukeException If the task description is empty or the doAfter date is invalid.
     */
    public DoAfter(String description) throws DukeException {
        super(description);
        initializeDoAfter();
    }

    /** Initializes the DoAfter task.
     * @throws DukeException If the task description is empty or the doAfter date is invalid.
     */
    private void initializeDoAfter() throws DukeException {
        String[] splitString = description.split("/after", 2);
        if (splitString.length != 2) throw new InvalidTaskFormatException();
        assert !splitString[0].isBlank() : "description should not be empty";
        this.description = splitString[0].trim();
        assert !splitString[1].isBlank() : "doAfter should not be empty";
        String doAfterString = splitString[1].trim();
        this.doAfter = super.parseDate(doAfterString);
    }

    /**
     * Creates a new DoAfter task.
     * @param description The description of the task.
     * @param deadlineString The doAfter date of the task in yyyy-MM-dd format.
     */
    public DoAfter(String description, String deadlineString) {
        super(description);
        // doAfter will be in default yyyy-MM-dd format which can be parsed by LocalDate
        this.doAfter = LocalDate.parse(deadlineString);
    }

    @Override
    public String getStatusIcon() {
        return "[DA]" + super.getStatusIcon();
    }

    @Override
    public String toString() {
        if (doAfter == null) return super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return super.toString() + String.format(" (by: %s)",doAfter.format(formatter));
    }

    @Override
    public String toFileString() {
        return "DA | " + super.toFileString() + (doAfter == null ? "" : " | " + doAfter);
    }

    /**
     * Marks the task as done. Throws an exception if the task is marked as done before the doAfter date.
     * @throws TaskSpecificException
     */
    @Override
    public void markAsDone() throws TaskSpecificException {
        if (doAfter.isAfter(LocalDate.now())) {
            throw new TaskSpecificException("DoAfter task cannot be marked as done before the doAfter date");
        } else {
            super.markAsDone();
        }
    }
}

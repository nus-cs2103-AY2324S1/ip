package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate by;


    /**
     * Constructor for duke.task.DeadlineTask.
     *
     * @param description of the task.
     * @param by          deadline of the task.
     */
    public DeadlineTask(String description, String by) throws DukeException {
        super(description);
        try {

            this.by = LocalDate.parse(by);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date in the format: yyyy-mm-dd");
        }
    }

    @Override
    public String getFileDescriptor() {
        return super.getFileDescriptor() + String.format("| %s | %s", this.by.toString(), "DEADLINE");
    }

    /**
     * Get the deadline of the task.
     *
     * @return deadline of the task.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + this.getBy() + ")";
    }
}

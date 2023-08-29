package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate deadline;

    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d1 = LocalDate.parse(deadline, formatter);
            this.deadline = d1;
        } catch (Exception e) {
            throw new DukeException("Please provide a deadline in the format yyyy-MM-dd");
        }
    }

    @Override
    public String getDescription() {
        String dueDate = this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D] " + super.getDescription()
                + " (by: " + dueDate + ")";
    }

    @Override
    public String savedString() {
        return "D " + super.savedString() + " | " + this.deadline;
    }
}

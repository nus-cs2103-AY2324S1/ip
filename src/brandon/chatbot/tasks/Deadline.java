package tasks;

import common.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String title, String deadline) throws Exception {
        super(title);
        if (deadline.isBlank()) {
            throw new DukeException("    Deadline cannot be blank...\n--------------------------------");
        }
        try {
            String dateFormat = "yyyy-MM-dd";
            LocalDate d1 = LocalDate.parse(deadline, DateTimeFormatter.ofPattern(dateFormat));
            this.deadline = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            throw new Exception("Could you try your date in yyyy-mm-dd format instead...?");
        }
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "Deadline";
    }
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: " + this.deadline + ")";
    }


}

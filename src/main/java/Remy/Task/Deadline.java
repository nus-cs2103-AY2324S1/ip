package Remy.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws DateTimeParseException {

        super(description);
        // Accepts time String in yyyy-MM-dd format
        // Stores as dd MMM yyyy format
        LocalDate temp = LocalDate.parse(by);
        this.by = temp.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

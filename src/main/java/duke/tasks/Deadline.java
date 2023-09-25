package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    protected boolean isDate = true;

    public Deadline(String description, String by) {
        super(description);

        //To treat the deadline as a date, the user must input it as either of the following formats.
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        try {
            this.date = LocalDate.parse(by, formatter1);
        } catch (DateTimeParseException eDate) {
            try {
                this.date = LocalDate.parse(by, formatter2);
            } catch (DateTimeParseException eDate2) {
                isDate = false;
                this.by = by;
            }
        }
    }

    public String getBy() {
        if (!isDate) {
            return this.by;
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter1);
    }

    @Override
    public String storedString() {
        return "D | " + super.storedString() + " | " + getBy();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}


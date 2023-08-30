import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            formatDate(by);
        } catch (DateTimeException e) {
            throw new DukeException("Please input your deadline in YYYY-MM-DD format!");
        }
    }

    public void formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(date, formatter);
    }

    @Override
    public String save() {
        return "D|" + super.save() + "|" + this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

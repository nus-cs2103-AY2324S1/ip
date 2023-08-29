import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (!Input.isValidDate(by, formatter)) {
            throw new InvalidDateTimeException();
        }
        this.by = LocalDateTime.parse(by, formatter);
    }

    @Override
    public String toString() {
        return String.format("[D] [%s] %s (by: %s)",
                this.getStatusIcon(),
                super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }
}

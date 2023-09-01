import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws FishronException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid date/time format. e.g. 21-05-2023 1200");
        }
    }

    public String toFileString() {
        return "D | " + super.toFileString() + " | " + by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma")) + ")";
    }
}
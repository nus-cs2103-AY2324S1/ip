import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Deadline extends Task {
    protected LocalDate d_time;

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String d_time) throws DukeException {
        super(description, TaskType.DEADLINE);
//        this.d_time = d_time;

        try {
            this.d_time = LocalDate.parse(d_time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format for input: " + d_time);
        }
    }

    public Deadline(String description, String d_time, boolean isDone) throws DukeException {
        super(description, TaskType.DEADLINE, isDone);
//        this.d_time = d_time;

        try {
            this.d_time = LocalDate.parse(d_time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format for input: " + d_time);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + d_time.format(OUTPUT_FORMATTER) + ")";
    }
}